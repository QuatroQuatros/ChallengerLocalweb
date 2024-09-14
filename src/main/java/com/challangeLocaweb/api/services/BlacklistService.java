package com.challangeLocaweb.api.services;

import com.challangeLocaweb.api.dtos.blacklist.BlacklistCreateDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistResponseDTO;
import com.challangeLocaweb.api.dtos.blacklist.BlacklistUpdateDTO;
import com.challangeLocaweb.api.models.Blacklist;

public interface BlacklistService extends CRUDInterface<Blacklist, Long, BlacklistCreateDTO, BlacklistUpdateDTO, BlacklistResponseDTO>{
}
