package org.medibook.Service.Patient;

import org.medibook.Dto.PatientDto.*;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PatientService {

    public PatientRegisterResponseDto registerPatient(PatientRegisterRequestDto patientRegisterRequestDto, User user) throws NotFoundException;

    public PatientProfileResponseDto getPatientProfile(User user) throws NotFoundException;

    public void updatePatientProfile(PatientProfileEditRequestDto patientProfileEditRequestDto, User user) throws NotFoundException;

    public void updatePatient(Long id, PatientProfileEditRequestDto patientProfileEditRequestDto) throws NotFoundException;

    public Page<PatientListResponseDto> getAllPatients(String name, String lastName, String dni, String gender, LocalDate birthDate, Pageable pageable);

}
