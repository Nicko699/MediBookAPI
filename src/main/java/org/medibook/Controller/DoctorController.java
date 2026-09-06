package org.medibook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.medibook.Dto.DoctorDto.DoctorEditRequestDto;
import org.medibook.Dto.DoctorDto.DoctorListResponseDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.Doctor.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@Tag(name = "Doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Operation(summary = "Update doctor by id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorEditRequestDto doctorEditRequestDto) throws NotFoundException{

        doctorService.updateDoctor(id, doctorEditRequestDto);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all doctors with filters")
    @GetMapping("/list")
    public ResponseEntity<Page<DoctorListResponseDto>> getAllDoctors(@RequestParam(required = false)  String name,@RequestParam(required = false)  String lastName, @RequestParam(required = false) String speciality, Pageable pageable){

        Page<DoctorListResponseDto> doctors=doctorService.getAllDoctors(name,lastName,speciality,pageable);

        return ResponseEntity.ok(doctors);

    }

}
