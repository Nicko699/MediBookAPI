package org.medibook.Service.Doctor;

import org.medibook.Dto.DoctorDto.DoctorRegisterRequestDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.User;

public interface DoctorService {

    public void registerDoctor(DoctorRegisterRequestDto doctorRegisterRequestDto, User user) throws NotFoundException;

}
