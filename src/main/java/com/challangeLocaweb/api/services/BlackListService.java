package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.blacklist.BlackListCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlackListUpdateDTO;
import com.challangeLocaweb.api.models.BlackList;

public interface BlackListService extends CRUDInterface<BlackList, Long, BlackListCreateDTO, BlackListUpdateDTO, BlackListResponseDTO>{

}
