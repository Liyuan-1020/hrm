package com.gec.hrm.mapper;

import com.gec.hrm.pojo.DocumentInf;
import com.gec.hrm.pojo.DocumentInfExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocumentInfMapper {
    int countByExample(DocumentInfExample example);

    int deleteByExample(DocumentInfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DocumentInf record);

    int insertSelective(DocumentInf record);

    List<DocumentInf> selectByExampleWithBLOBs(DocumentInfExample example);

    List<DocumentInf> selectByExample(DocumentInfExample example);

    DocumentInf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DocumentInf record, @Param("example") DocumentInfExample example);

    int updateByExampleWithBLOBs(@Param("record") DocumentInf record, @Param("example") DocumentInfExample example);

    int updateByExample(@Param("record") DocumentInf record, @Param("example") DocumentInfExample example);

    int updateByPrimaryKeySelective(DocumentInf record);

    int updateByPrimaryKeyWithBLOBs(DocumentInf record);

    int updateByPrimaryKey(DocumentInf record);
}