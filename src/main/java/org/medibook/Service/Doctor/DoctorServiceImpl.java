package org.medibook.Service.Doctor;

import org.medibook.Dto.DoctorDto.DoctorProfileResponseDto;
import org.medibook.Dto.DoctorDto.DoctorRegisterRequestDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Doctor;
import org.medibook.Model.User;
import org.medibook.Repository.DoctorRepository;
import org.medibook.Repository.SpecialityRepository;
import org.medibook.mapper.DoctorMapper;
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

        Doctor doctor=doctorRepository.findById(user.getDoctor().getId()).orElseThrow(
                ()->new NotFoundException("Doctor no encontrado en el sistema"));

        return doctorMapper.doctorToDoctorProfileResponse(doctor);
    }

}
