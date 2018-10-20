package be.axa.life.corp.dailvieservices.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.axa.life.corp.dailvieservices.model.TbHistoricContractAffVo;

public class TbHistoricContractAffRowMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		TbHistoricContractAffVo tbHistoricContractAffVo = new TbHistoricContractAffVo();
		tbHistoricContractAffVo.setDemandDate(rs.getString("DEMAND_DATE"));
		tbHistoricContractAffVo.setDemandNature(rs.getString("DEMAND_NATURE").trim());
		tbHistoricContractAffVo.setDemandStatus(rs.getString("DEMAND_STATUS").trim());
		tbHistoricContractAffVo.setDemandUserId(rs.getString("DEMAND_USER_ID"));
		tbHistoricContractAffVo.setPoliceNumber(rs.getString("POLICE_NUMBER"));
		tbHistoricContractAffVo.setEmployerNumber(rs.getString("EMPLOYER_NUMBER").trim());
		tbHistoricContractAffVo.setEmployerName(rs.getString("EMPLOYER_NAME"));
		tbHistoricContractAffVo.setInsuredName(rs.getString("INSURED_NAME"));
		tbHistoricContractAffVo.setInsuredNiss(rs.getString("INSURED_NATIONAL_ID"));
		tbHistoricContractAffVo.setEastmanId(rs.getString("EASTMAN_ID"));
		String statusOnMF = rs.getString("STATUS_ON_MF");
		if(statusOnMF!=null){
		tbHistoricContractAffVo.setStatusOnMf(statusOnMF.trim());
		}
		tbHistoricContractAffVo.setPs2Link(rs.getString("PS2_LINK"));
		tbHistoricContractAffVo.setAffiliationType(rs.getString("TYPE_OF_AFFILIATION").trim());
		tbHistoricContractAffVo.setBirthDate(rs.getString("BIRTHDATE"));
		tbHistoricContractAffVo.setAno(rs.getString("CODE_ANO"));
		tbHistoricContractAffVo.setPdfGenerated(rs.getString("PDF_GENERATED").trim());
		tbHistoricContractAffVo.setLobData(rs.getString("LOB_DATA"));
		tbHistoricContractAffVo.setMartriculeId(rs.getString("MATRICULE_ID"));
		tbHistoricContractAffVo.setTreatmentDate(rs.getString("TREATMENT_DATE"));
		tbHistoricContractAffVo.setDemandKey(rs.getString("DEMAND_KEY"));
		tbHistoricContractAffVo.setConnectAs(rs.getString("CONNECT_AS"));
		return tbHistoricContractAffVo;
	}

}
