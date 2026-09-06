package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.medibook.Dto.PatientDto.*;
import org.medibook.Model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientRegisterRequestDtoToPatient(PatientRegisterRequestDto patientRegisterRequestDto);

    PatientRegisterResponseDto patientToPatientRegisterResponseDto(Patient patient);

    PatientProfileResponseDto patientToPatientProfileResponseDto(Patient patient);

    @Mapping(target = "user", ignore = true)
    void updatePatientFromPatientProfileEditRequestDto(PatientProfileEditRequestDto patientProfileEditRequestDto, @MappingTarget Patient patient);

    void updatePatientRequestDtoToPatient(PatientProfileEditRequestDto patientProfileEditRequestDto, @MappingTarget Patient patient);

    PatientListResponseDto patientToPatientListResponseDto(Patient patient);


}
