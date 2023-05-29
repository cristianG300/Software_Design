package com.sd_utcn.secondHand.model;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.sd_utcn.secondHand.model.resources.Messages;

import jakarta.persistence.*;

@Table(name = "reports")
@Entity
public class Report {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id_report;

    @Column(name = "id", nullable = false)
    private int id = 0;
    
    @Column(name = "reportType", nullable = false)
    private ReportType reportType;
    
    @Column(name = "report", nullable = false)
    private StringBuilder report;
    
    @Column(name = "itemId", nullable = false)
    private int itemId;
    
    public Report(final String addOnReport, final ReportType reportType, final int itemId) {
        this.report = new StringBuilder(Messages.REPORT_INIT + Integer.toString(id) + ": " + reportType.toString() + addOnReport);
        this.id = incrementId();
        this.itemId = itemId;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId_report() {
        return id_report;
    }

    public void setId_report(UUID id_report) {
        this.id_report = id_report;
    }
    
    public Report getReport(final int id) {
        if (id == this.id) {
            return this;
        }
        return null;
    }

    public int getId() {
        return id;
    }
    
    public ReportType getReportType() {
        return reportType;
    }
    
    public int getItemId() {
        return this.itemId;
    }
    
    private int incrementId() {
        return id++;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o.getClass() != Report.class) {
            return false;
        }
        return this.id == ((Report) o).getId();
    }
    
    @Override
    public String toString() {
        return this.report.toString();
    }
    

}
