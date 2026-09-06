package org.medibook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.medibook.Dto.PatientDto.PatientListResponseDto;
import org.medibook.Dto.PatientDto.PatientProfileEditRequestDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.Patient.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/patient")
@Tag(name = "Patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Update a patient by id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientProfileEditRequestDto patientProfileEditRequestDto ) throws NotFoundException{

        patientService.updatePatient(id, patientProfileEditRequestDto);

        return  ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all patients with filters")
    @GetMapping("/list")
    public ResponseEntity<Page<PatientListResponseDto>> getAllPatients(@RequestParam(required = false)  String name,@RequestParam(required = false) String lastName,@RequestParam(required = false) String dni,@RequestParam(required = false) String gender,@RequestParam(required = false) LocalDate birthDate, Pageable pageable){

        Page<PatientListResponseDto> patients=patientService.getAllPatients(name,lastName,dni,gender,birthDate,pageable);

        return ResponseEntity.ok(patients);
    }



}
