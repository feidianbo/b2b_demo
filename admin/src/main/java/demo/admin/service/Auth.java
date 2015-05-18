package demo.admin.service;

import demo.core.domain.Admin;
import demo.core.persistence.AdminMapper;
import demo.core.persistence.UserRoleMapper;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joe on 11/2/14.
 */
@Service
public class Auth {
    @Autowired
    private Session session;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

	//登陆
    public boolean login(String username, String password){
        if(username != null) {
            Admin admin = adminMapper.getByUsername(username);
            if (admin != null && admin.getPassword().equals(DigestUtils.md5Hex(password)) && admin.isIsactive()) {
                session.login(admin, userRoleMapper.getRoleListByUserid(admin.getId()));
                return true;
            }
        }
        return false;
    }

	//根据汉字获取拼音
	public String HanyuToPinyin(String name){
		String pinyinName = "";
		char[] nameChar = name.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i=0; i<nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return pinyinName;
	}
}
