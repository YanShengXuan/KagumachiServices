package tw.com.services.kagumachi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "website")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer websiteid;

    private String websitename;
    private String aboutus;
    private String qa;
    private String logo;
    private String footerinstagramlink;
    private String footerfacebooklink;

    public Integer getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(Integer websiteid) {
        this.websiteid = websiteid;
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getQa() {
        return qa;
    }

    public void setQa(String qa) {
        this.qa = qa;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFooterinstagramlink() {
        return footerinstagramlink;
    }

    public void setFooterinstagramlink(String footerinstagramlink) {
        this.footerinstagramlink = footerinstagramlink;
    }

    public String getFooterfacebooklink() {
        return footerfacebooklink;
    }

    public void setFooterfacebooklink(String footerfacebooklink) {
        this.footerfacebooklink = footerfacebooklink;
    }
}
