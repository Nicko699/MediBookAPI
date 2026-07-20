package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.medibook.Dto.PatientDto.PatientRegisterRequestDto;
import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;
import org.medibook.Model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientRegisterRequestDtoToPatient(PatientRegisterRequestDto patientRegisterRequestDto);

    @Mapping(source = "user", target = "userRegister")
    PatientRegisterResponseDto patientToPatientRegisterResponseDto(Patient patient);
}
