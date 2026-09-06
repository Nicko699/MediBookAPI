package org.medibook.Service.Doctor;

import org.medibook.Dto.DoctorDto.*;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Doctor;
import org.medibook.Model.User;
import org.medibook.Repository.DoctorRepository;
import org.medibook.Repository.SpecialityRepository;
import org.medibook.Specifications.DoctorSpecification;
import org.medibook.mapper.DoctorMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;

@Service
public class DoctorServiceImpl implements DoctorService {

  private final DoctorMapper doctorMapper;
  private final DoctorRepository doctorRepository;
  private final SpecialityRepository specialityRepository;

    public DoctorServiceImpl(DoctorMapper doctorMapper, DoctorRepository doctorRepository, SpecialityRepository specialityRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public void registerDoctor(DoctorRegisterRequestDto doctorRegisterRequestDto, User user) throws NotFoundException {

        Doctor doctor=doctorMapper.doctorRegisterRequestToDoctor(doctorRegisterRequestDto);

        boolean specialityExist=specialityRepository.existsById(doctor.getSpeciality().getId());

        if (!specialityExist) {
            throw new NotFoundException("Especialidad no encontrada en el sistema");
        }

        doctor.setActive(true);
        doctor.setSoftDelete(false);
        doctor.setCreatedAt(Instant.now());
        doctor.setUpdatedAt(doctor.getCreatedAt());
        doctor.setUser(user);
        doctor.setSpeciality(doctor.getSpeciality());

        doctorRepository.save(doctor);

    }

    @Transactional(readOnly = true)
    @Override
    public DoctorProfileResponseDto getDoctorProfile(User user) throws NotFoundException {

        Doctor doctor=doctorRepository.findByIdAndSoftDeleteFalse(user.getDoctor().getId()).orElseThrow(
                ()->new NotFoundException("Doctor no encontrado en el sistema"));

        return doctorMapper.doctorToDoctorProfileResponse(doctor);
    }

    @Transactional
    @Override
    public void updateDoctorProfile(DoctorProfileEditRequestDto doctorProfileEditRequestDto, User user) throws NotFoundException {

        Doctor doctor=doctorRepository.findByIdAndSoftDeleteFalse(user.getDoctor().getId()).orElseThrow(
                ()->new NotFoundException("Doctor no encontrado en el sistema"));

        doctorMapper.updateDoctorProfileFromRequest(doctorProfileEditRequestDto,doctor);

        doctor.setUpdatedAt(Instant.now());

        doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public void updateDoctor(Long id, DoctorEditRequestDto doctorEditRequestDto) throws NotFoundException {

        Doctor doctor=doctorRepository.findByIdAndSoftDeleteFalse(id).orElseThrow(
                ()->new NotFoundException("Doctor no encontrado en el sistema"));

        doctorMapper.updateDoctorRequestDtoToDoctor(doctorEditRequestDto,doctor);

        doctor.setUpdatedAt(Instant.now());
        doctor.getUser().setUpdatedAt(Instant.now());

        doctorRepository.save(doctor);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<DoctorListResponseDto> getAllDoctors(String name, String lastName, String speciality, Pageable pageable) {

        Specification<Doctor> spec = Specification.allOf(DoctorSpecification.noDeleted()
                .and(DoctorSpecification.nameLike(name)
                        .and(DoctorSpecification.lastNameLike(lastName)
                                .and(DoctorSpecification.specialityLike(speciality)))));

        Page<Doctor> doctors = doctorRepository.findAll(spec, pageable);

        return doctors.map(doctorMapper::doctorToDoctorListResponseDto);

    }

}
