package demo.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jack on 15/3/4.
 */
public class Frame implements Serializable{
    private Menu menu;
    private List<Menu> menusList;

    public Frame(){}

    public Frame(Menu menu, List<Menu> menusList) {
        this.menu = menu;
        this.menusList = menusList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<Menu> menusList) {
        this.menusList = menusList;
    }
}
