package be.axa.life.corp.dailvieservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.axa.life.corp.dailvieservices.model.TbModificationAskedVo;

public class TbModificationAskedRowMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		TbModificationAskedVo tbModificationAskedVo = new TbModificationAskedVo();
		tbModificationAskedVo.setDemandDate(rs.getString("DEMAND_DATE"));
		tbModificationAskedVo.setDemandNature(rs.getString("DEMAND_NATURE").trim());
		tbModificationAskedVo.setDemandStatus(rs.getString("DEMAND_STATUS").trim());
		tbModificationAskedVo.setDemandUserId(rs.getString("DEMAND_USER_ID"));
		tbModificationAskedVo.setPoliceNumber(rs.getString("POLICE_NUMBER"));
		tbModificationAskedVo.setEmployerNumber(rs.getString("EMPLOYER_NUMBER").trim());
		tbModificationAskedVo.setEmployerName(rs.getString("EMPLOYER_NAME"));
		tbModificationAskedVo.setInsuredName(rs.getString("INSURED_NAME"));
		tbModificationAskedVo.setInsuredNiss(rs.getString("INSURED_NATIONAL_ID"));
		tbModificationAskedVo.setEastmanId(rs.getString("EASTMAN_ID"));
		String statusOnMF = rs.getString("STATUS_ON_MF");
		if(statusOnMF!=null){
		tbModificationAskedVo.setStatusOnMf(statusOnMF.trim());
		}
		tbModificationAskedVo.setPs2Link(rs.getString("PS2_LINK"));
		tbModificationAskedVo.setAffiliationType(rs.getString("TYPE_OF_AFFILIATION").trim());
		tbModificationAskedVo.setBirthDate(rs.getString("BIRTHDATE"));
		tbModificationAskedVo.setAno(rs.getString("CODE_ANO"));
		tbModificationAskedVo.setPdfGenerated(rs.getString("PDF_GENERATED").trim());
		tbModificationAskedVo.setLobData(rs.getString("LOB_DATA"));
		tbModificationAskedVo.setMartriculeId(rs.getString("MATRICULE_ID"));
		tbModificationAskedVo.setTreatmentDate(rs.getString("TREATMENT_DATE"));
		tbModificationAskedVo.setDemandKey(rs.getString("DEMAND_KEY"));
		tbModificationAskedVo.setConnectAs(rs.getString("CONNECT_AS"));
		return tbModificationAskedVo;
	}

}
