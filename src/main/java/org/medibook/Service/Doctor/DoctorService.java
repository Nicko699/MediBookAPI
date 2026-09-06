package org.medibook.Service.Doctor;

import org.medibook.Dto.DoctorDto.*;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

    public void registerDoctor(DoctorRegisterRequestDto doctorRegisterRequestDto, User user) throws NotFoundException;

    public DoctorProfileResponseDto getDoctorProfile(User user) throws NotFoundException;

    public void updateDoctorProfile(DoctorProfileEditRequestDto doctorProfileEditRequestDto, User user) throws NotFoundException;

    public void updateDoctor(Long id, DoctorEditRequestDto doctorEditRequestDto) throws NotFoundException;

    public Page<DoctorListResponseDto> getAllDoctors(String name, String lastName, String speciality, Pageable pageable);

}
