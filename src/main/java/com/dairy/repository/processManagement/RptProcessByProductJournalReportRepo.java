package com.dairy.repository.processManagement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dairy.model.processManagement.RptProcessByProductJournalReport;



@Repository
@EnableJpaRepositories
public interface RptProcessByProductJournalReportRepo  extends JpaRepository<RptProcessByProductJournalReport, Integer>
{
     @Transactional
     int deleteByid(int id);
     
     //List<RptProcessByProductJournalReport> findByDateBetween(String fDate,String tDate);
     List<RptProcessByProductJournalReport>findByDateBetween(String getfDate,String gettDate);
}
