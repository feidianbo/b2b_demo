package demo.core.domain;

import java.io.Serializable;

/**
 * Created by jack on 14/11/23.
 * CompVerSus----Company Verify Success
 * 意思是：公司已验证成功信息  备份公司信息
 */
public class CompVerSus extends Company implements Serializable {
    public CompVerSus(String name, String address, String phone, String fax, String legalperson, String businesslicense, String identificationnumber, String organizationcode, String operatinglicense, int userid, String legalpersonname, String account, String openingbank) {
        super(name, address, phone, fax, legalperson, businesslicense, identificationnumber, organizationcode, operatinglicense, userid, legalpersonname, account, openingbank);
    }
}
