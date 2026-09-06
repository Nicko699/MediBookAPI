package org.medibook.Service.Patient;

import org.medibook.Dto.PatientDto.*;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Patient;
import org.medibook.Model.User;
import org.medibook.Repository.PatientRepository;
import org.medibook.Specifications.PatientSpecification;
import org.medibook.mapper.PatientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;

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

    @Transactional(readOnly = true)
    @Override
    public PatientProfileResponseDto getPatientProfile(User user) throws NotFoundException {

        Patient patient=patientRepository.findById(user.getPatient().getId()).orElseThrow(
                ()->new NotFoundException("Paciente no encontrado en el sistema"));

        return patientMapper.patientToPatientProfileResponseDto(patient);
    }

   @Transactional
    @Override
    public void updatePatientProfile(PatientProfileEditRequestDto patientProfileEditRequestDto, User user) throws NotFoundException {

        Patient patient=patientRepository.findById(user.getPatient().getId()).orElseThrow(
                ()->new NotFoundException("Paciente no encontrado en el sistema"));

        patientMapper.updatePatientFromPatientProfileEditRequestDto(patientProfileEditRequestDto,patient);

        patient.setUpdatedAt(Instant.now());

        patientRepository.save(patient);
    }

    @Transactional
    @Override
    public void updatePatient(Long id, PatientProfileEditRequestDto patientProfileEditRequestDto) throws NotFoundException {

        Patient patient=patientRepository.findByIdAndSoftDeleteFalse(id).orElseThrow(
                ()->new NotFoundException("Paciente no encontrado en el sistema"));

        patientMapper.updatePatientRequestDtoToPatient(patientProfileEditRequestDto,patient);

        patient.setUpdatedAt(Instant.now());
        patient.getUser().setUpdatedAt(Instant.now());

        patientRepository.save(patient);


    }

    @Transactional(readOnly = true)
    @Override
    public Page<PatientListResponseDto> getAllPatients(String name, String lastName, String dni, String gender, LocalDate birthDate, Pageable pageable) {

        Specification<Patient> spec = Specification.allOf(PatientSpecification.noDeleted()
                .and(PatientSpecification.nameLike(name)
                        .and(PatientSpecification.lastNameLike(lastName)
                                .and(PatientSpecification.dniLike(dni)
                                        .and(PatientSpecification.genderEqual(gender)
                                                .and(PatientSpecification.birthDateEqual(birthDate)))))));


            Page<Patient> patients = patientRepository.findAll(spec, pageable);


        return patients.map(patientMapper::patientToPatientListResponseDto);
    }
}
