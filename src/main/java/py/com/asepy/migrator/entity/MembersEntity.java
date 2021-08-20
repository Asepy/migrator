package py.com.asepy.migrator.entity;

import py.com.asepy.migrator.converter.YearAttributeConverter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "members2", schema = "members")
public class MembersEntity {
    private String website;
    private String surname;
    private Timestamp startDate;
    private String rucStatus;
    private String ruc;
    private Integer paymentOption;
    private Integer numberEmployees;
    private String nationalId;
    private String name;
    private String membershipType;
    private Integer mcc;
    private String mailId;
    private String emailStatus;
    private String businessName;
    private Integer cityId;
    private String cellphone;
    private Date birthdate;
    private String id;
    private Timestamp confirmationDate;
    private String fancyBusinessName;
    private String annualTurnover;
    private String status;
    private Long deparmentId;
    private String gender;
    private String address;
    private String educationLevel;
    private String linkedinProfile;
    private String twitterProfile;
    private String facebookProfile;
    private String businessOrgType;
    private String companyRole;
    private Year startedBusinessYear;
    private String birhtdate;
    private String departmentId;
    private String sector;
    private Long rubroId;
    private String memberDefinedRubro;
    private String plusBillingAddress;
    private String plusPaymentMethod;
    private Integer refId;
    /*private String startName;*/

    @Basic
    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "ruc_status")
    public String getRucStatus() {
        return rucStatus;
    }

    public void setRucStatus(String rucStatus) {
        this.rucStatus = rucStatus;
    }

    @Basic
    @Column(name = "ruc")
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Basic
    @Column(name = "payment_option")
    public Integer getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(Integer paymentOption) {
        this.paymentOption = paymentOption;
    }

    @Basic
    @Column(name = "number_employees")
    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    @Basic
    @Column(name = "national_id")
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "membership_type")
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Basic
    @Column(name = "mcc")
    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    @Basic
    @Column(name = "mail_id")
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Basic
    @Column(name = "email_status")
    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    @Basic
    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Basic
    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "cellphone")
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "confirmation_date")
    public Timestamp getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Timestamp confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    @Basic
    @Column(name = "fancy_business_name")
    public String getFancyBusinessName() {
        return fancyBusinessName;
    }

    public void setFancyBusinessName(String fancyBusinessName) {
        this.fancyBusinessName = fancyBusinessName;
    }

    @Basic
    @Column(name = "annual_turnover")
    public String getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(String annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "deparment_id")
    public Long getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(Long deparmentId) {
        this.deparmentId = deparmentId;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "education_level")
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Basic
    @Column(name = "linkedin_profile")
    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    @Basic
    @Column(name = "twitter_profile")
    public String getTwitterProfile() {
        return twitterProfile;
    }

    public void setTwitterProfile(String twitterProfile) {
        this.twitterProfile = twitterProfile;
    }

    @Basic
    @Column(name = "facebook_profile")
    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    @Basic
    @Column(name = "business_org_type")
    public String getBusinessOrgType() {
        return businessOrgType;
    }

    public void setBusinessOrgType(String businessOrgType) {
        this.businessOrgType = businessOrgType;
    }

    @Basic
    @Column(name = "company_role")
    public String getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(String companyRole) {
        this.companyRole = companyRole;
    }

    @Basic
    @Column(name = "started_business_year")
    @Convert(
            converter = YearAttributeConverter.class
    )
    public Year getStartedBusinessYear() {
        return startedBusinessYear;
    }

    public void setStartedBusinessYear(Year startedBusinessYear) {
        this.startedBusinessYear = startedBusinessYear;
    }

    @Basic
    @Column(name = "birhtdate")
    public String getBirhtdate() {
        return birhtdate;
    }

    public void setBirhtdate(String birhtdate) {
        this.birhtdate = birhtdate;
    }

    @Basic
    @Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "sector")
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Basic
    @Column(name = "rubro_id")
    public Long getRubroId() {
        return rubroId;
    }

    public void setRubroId(Long rubroId) {
        this.rubroId = rubroId;
    }

    @Basic
    @Column(name = "member_defined_rubro")
    public String getMemberDefinedRubro() {
        return memberDefinedRubro;
    }

    public void setMemberDefinedRubro(String memberDefinedRubro) {
        this.memberDefinedRubro = memberDefinedRubro;
    }

    @Basic
    @Column(name = "plus_billing_address")
    public String getPlusBillingAddress() {
        return plusBillingAddress;
    }

    public void setPlusBillingAddress(String plusBillingAddress) {
        this.plusBillingAddress = plusBillingAddress;
    }

    @Basic
    @Column(name = "plus_payment_method")
    public String getPlusPaymentMethod() {
        return plusPaymentMethod;
    }

    public void setPlusPaymentMethod(String plusPaymentMethod) {
        this.plusPaymentMethod = plusPaymentMethod;
    }

    @Basic
    @Column(name = "ref_id")
    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    /*@Basic
    @Column(name = "start_name")
    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembersEntity that = (MembersEntity) o;
        return Objects.equals(website, that.website) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(rucStatus, that.rucStatus) &&
                Objects.equals(ruc, that.ruc) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(numberEmployees, that.numberEmployees) &&
                Objects.equals(nationalId, that.nationalId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(membershipType, that.membershipType) &&
                Objects.equals(mcc, that.mcc) &&
                Objects.equals(mailId, that.mailId) &&
                Objects.equals(emailStatus, that.emailStatus) &&
                Objects.equals(businessName, that.businessName) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(cellphone, that.cellphone) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(id, that.id) &&
                Objects.equals(confirmationDate, that.confirmationDate) &&
                Objects.equals(fancyBusinessName, that.fancyBusinessName) &&
                Objects.equals(annualTurnover, that.annualTurnover) &&
                Objects.equals(status, that.status) &&
                Objects.equals(deparmentId, that.deparmentId) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(educationLevel, that.educationLevel) &&
                Objects.equals(linkedinProfile, that.linkedinProfile) &&
                Objects.equals(twitterProfile, that.twitterProfile) &&
                Objects.equals(facebookProfile, that.facebookProfile) &&
                Objects.equals(businessOrgType, that.businessOrgType) &&
                Objects.equals(companyRole, that.companyRole) &&
                Objects.equals(startedBusinessYear, that.startedBusinessYear) &&
                Objects.equals(birhtdate, that.birhtdate) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(sector, that.sector) &&
                Objects.equals(rubroId, that.rubroId) &&
                Objects.equals(memberDefinedRubro, that.memberDefinedRubro) &&
                Objects.equals(plusBillingAddress, that.plusBillingAddress) &&
                Objects.equals(plusPaymentMethod, that.plusPaymentMethod) &&
                Objects.equals(refId, that.refId) ;
                /*Objects.equals(startName, that.startName);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, surname, startDate, rucStatus, ruc, paymentOption, numberEmployees, nationalId, name, membershipType, mcc, mailId, emailStatus, businessName, cityId, cellphone, birthdate, id, confirmationDate, fancyBusinessName, annualTurnover, status, deparmentId, gender, address, educationLevel, linkedinProfile, twitterProfile, facebookProfile, businessOrgType, companyRole, startedBusinessYear, birhtdate, departmentId, sector, rubroId, memberDefinedRubro, plusBillingAddress, plusPaymentMethod, refId/*, startName*/);
    }

    @Override
    public String toString() {
        return "MembersEntity{" +
                "website='" + website + '\'' +
                ", surname='" + surname + '\'' +
                ", startDate=" + startDate +
                ", rucStatus='" + rucStatus + '\'' +
                ", ruc='" + ruc + '\'' +
                ", paymentOption=" + paymentOption +
                ", numberEmployees=" + numberEmployees +
                ", nationalId='" + nationalId + '\'' +
                ", name='" + name + '\'' +
                ", membershipType='" + membershipType + '\'' +
                ", mcc=" + mcc +
                ", mailId='" + mailId + '\'' +
                ", emailStatus='" + emailStatus + '\'' +
                ", businessName='" + businessName + '\'' +
                ", cityId=" + cityId +
                ", cellphone='" + cellphone + '\'' +
                ", birthdate=" + birthdate +
                ", id='" + id + '\'' +
                ", confirmationDate=" + confirmationDate +
                ", fancyBusinessName='" + fancyBusinessName + '\'' +
                ", annualTurnover='" + annualTurnover + '\'' +
                ", status='" + status + '\'' +
                ", deparmentId=" + deparmentId +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", linkedinProfile='" + linkedinProfile + '\'' +
                ", twitterProfile='" + twitterProfile + '\'' +
                ", facebookProfile='" + facebookProfile + '\'' +
                ", businessOrgType='" + businessOrgType + '\'' +
                ", companyRole='" + companyRole + '\'' +
                ", startedBusinessYear=" + startedBusinessYear +
                ", birhtdate='" + birhtdate + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", sector='" + sector + '\'' +
                ", rubroId=" + rubroId +
                ", memberDefinedRubro='" + memberDefinedRubro + '\'' +
                ", plusBillingAddress='" + plusBillingAddress + '\'' +
                ", plusPaymentMethod='" + plusPaymentMethod + '\'' +
                ", refId=" + refId +
                /*", startName='" + startName + '\'' +*/
                '}';
    }
}
