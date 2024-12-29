package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;
    /**
     * 查询产品：可按编号精确查询，按名称模糊查询，展示产品编号、名称、基
     * 本信息、负责人、产品阶段等字段
     */
    @RequestMapping("/query")
    public BaseResponse<List<ProductQueryViewDTO>> query(@RequestBody QueryRequestVo queryRequestVo) {
        RDMPageVO pageVO = new RDMPageVO(1, 10);
        return ResultUtils.success(productService.query(queryRequestVo));
    }

    /**
     * 新增产品：可创建单个产品
     * 创建内容：至少包含产品名称、负责人字段
     * 产品阶段默认为初始阶段
     */
    @RequestMapping("/create")
    public BaseResponse<ProductViewDTO> create(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResultUtils.success(productService.create(productCreateDTO));
    }

//    /**
//     * 修改产品：点击“修改”按钮，可编辑该产品信息：名称、基本信息、负责人、
//     * 产品阶段字段。（产品阶段只能由管理员修改）
//     */
//    @RequestMapping("/update")
//    public BaseResponse<ProductUpdateDTO> update() {
//        ResultUtils.success(productService.update());
//    }
//
//    /**
//     * 删除产品：点击“删除”按钮，可删除该产品
//     * 产品详情：点击“查看详情”按钮，可查看该产品信息：产品编号、名称、基本信
//     * 息、负责人、产品阶段、关联蓝图和部件编号。
//     */
//    @RequestMapping("/delete")
//    public String delete() {
//        ResultUtils.success(productService.delete());
//    }
//
//    /**
//     * 编辑并展示关联蓝图：可创建删除该产品与蓝图关系（形式自由）
//     * 注意：仅当产品处于概念化和设计阶段时，方可编辑
//     */
//    @RequestMapping("/blueprint")
//    public String blueprint() {
//        ResultUtils.success(productService.blueprint());
//    }
//
//    /**
//     * 编辑并展示关联部件：可创建删除该产品与部件关系（形式自由）
//     * 注意：仅当产品处于原型开发阶段时，方可编辑
//     */
//    @RequestMapping("/component")
//    public String component() {
//        ResultUtils.success(productService.component());
//    }
}
