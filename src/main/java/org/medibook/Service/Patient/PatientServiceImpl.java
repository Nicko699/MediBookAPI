package org.medibook.Service.Patient;

import org.medibook.Dto.PatientDto.PatientRegisterRequestDto;
import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Patient;
import org.medibook.Model.User;
import org.medibook.Repository.PatientRepository;
import org.medibook.mapper.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Transactional
    @Override
    public PatientRegisterResponseDto registerPatient(PatientRegisterRequestDto patientRegisterRequestDto, User user) throws NotFoundException {

   Patient patient= patientMapper.patientRegisterRequestDtoToPatient(patientRegisterRequestDto);

   patient.setActive(true);
   patient.setSoftDelete(false);
   patient.setCreatedAt(Instant.now());
   patient.setUpdatedAt(patient.getCreatedAt());
   patient.setUser(user);

   patientRepository.save(patient);

   return patientMapper.patientToPatientRegisterResponseDto(patient);

    }
}
