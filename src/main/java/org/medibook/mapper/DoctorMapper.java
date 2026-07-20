package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.medibook.Dto.DoctorDto.DoctorRegisterRequestDto;
import org.medibook.Model.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor doctorRegisterRequestToDoctor(DoctorRegisterRequestDto doctorRegisterRequestDto);


}
