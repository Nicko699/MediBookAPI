package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.medibook.Dto.AuditDto.AuditResponseDto;
import org.medibook.Model.Audit;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditResponseDto auditToAuditResponseDto(Audit audit);
}
