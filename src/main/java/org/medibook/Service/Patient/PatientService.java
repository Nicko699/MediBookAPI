package org.medibook.Service.Patient;

import org.medibook.Dto.PatientDto.PatientProfileResponseDto;
import org.medibook.Dto.PatientDto.PatientRegisterRequestDto;
import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.User;

public interface PatientService {

    public PatientRegisterResponseDto registerPatient(PatientRegisterRequestDto patientRegisterRequestDto, User user) throws NotFoundException;

    public PatientProfileResponseDto getPatientProfile(User user) throws NotFoundException;

}
