package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.medibook.Dto.DoctorDto.DoctorProfileEditRequestDto;
import org.medibook.Dto.DoctorDto.DoctorProfileResponseDto;
import org.medibook.Dto.DoctorDto.DoctorRegisterRequestDto;
import org.medibook.Model.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorRegisterRequestToDoctor(DoctorRegisterRequestDto doctorRegisterRequestDto);

    DoctorProfileResponseDto doctorToDoctorProfileResponse(Doctor doctor);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "speciality", ignore = true)
    void updateDoctorProfileFromRequest(DoctorProfileEditRequestDto doctorProfileEditRequestDto, @MappingTarget Doctor doctor);

}
