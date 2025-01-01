package com.example.iredms.service;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.dto.PartQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public interface PartService {
    PartViewDTO CreatePart(PartCreateDTO partCreate);
    PartViewDTO updatePart(PartUpdateDTO partUpdate);
    int deletePart(PartViewDTO partViewDTO);
    List<PartViewDTO> queryPartList(PartQueryDTO partQueryDTO);
}