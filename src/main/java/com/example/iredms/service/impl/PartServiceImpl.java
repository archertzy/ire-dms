package com.example.iredms.service.impl;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.dto.PartQueryDTO;
import com.example.iredms.service.PartService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.*;
import com.huawei.innovation.rdm.xdm.delegator.ClassificationNodeDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionDelegator;
import com.huawei.innovation.rdm.xdm.delegator.EXADefinitionLinkDelegator;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeViewDTO;
import com.huawei.innovation.rdm.xdm.dto.relation.EXADefinitionLinkViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartDelegator partDelegator;

    //创建部件
    @Override
    public PartViewDTO CreatePart(PartCreateDTO partCreate) {
        PartCreateDTO partCreateDTO = new PartCreateDTO();
        PartMasterCreateDTO pmcd = new PartMasterCreateDTO();
        partCreateDTO.setMaster(pmcd);
        PartBranchCreateDTO pbcd = new PartBranchCreateDTO();
        partCreateDTO.setBranch(pbcd);
        if (partCreate.getPartName() != null && !partCreate.getPartName().isEmpty()) {
            partCreateDTO.setPartName(partCreate.getPartName());
        }
        if (partCreate.getDescription() != null && !partCreate.getDescription().isEmpty()) {
            partCreateDTO.setDescription(partCreate.getDescription());
        }
        if (partCreate.getExtAttrs() != null && !partCreate.getExtAttrs().isEmpty()) {
            partCreateDTO.setExtAttrs(partCreate.getExtAttrs());
        }
        if (partCreate.getClsAttrs() != null && !partCreate.getClsAttrs().isEmpty()) {
            partCreateDTO.setClsAttrs(partCreate.getClsAttrs());
        }
        PartViewDTO createPart = partDelegator.create(partCreate);
        return createPart;
    }



    //更新部件
    @Override
    public PartViewDTO updatePart(PartUpdateDTO partUpdate) {
        return partDelegator.update(partUpdate);
    }

    //删除部件
    @Override
    public int deletePart(PartViewDTO partViewDTO) {
        if (partViewDTO.getId() != null) {
            QueryRequestVo queryRequestVo = new QueryRequestVo();
            queryRequestVo.addCondition("id", ConditionType.EQUAL, partViewDTO.getId());
            DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
            deleteByConditionVo.setCondition(queryRequestVo);
            return partDelegator.deleteByCondition(deleteByConditionVo);
        } else return 0;
    }

    @Override
    public List<PartViewDTO> queryPartList(PartQueryDTO partQueryDTO) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        if (partQueryDTO.getId() != null) {
            queryRequestVo.addCondition("id", ConditionType.EQUAL, partQueryDTO.getId());
        }
        if (partQueryDTO.getPartName() != null && !partQueryDTO.getPartName().isEmpty()) {
            queryRequestVo.addCondition("partName", ConditionType.LIKE, partQueryDTO.getPartName());
        }
        RDMPageVO pageVO = new RDMPageVO(1,Integer.MAX_VALUE);
        List<PartViewDTO> result;
        try {
            result = partDelegator.find(queryRequestVo, pageVO);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result;
    }


}
