package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsCreateDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsResponseDTO;
import com.challangeLocaweb.api.dtos.systemlabels.SystemLabelsUpdateDTO;
import com.challangeLocaweb.api.models.SystemLabels;

public interface SystemLabelsService extends CRUDInterface<SystemLabels, Long, SystemLabelsCreateDTO, SystemLabelsUpdateDTO, SystemLabelsResponseDTO> {

}
