package studio.papercube.capturity.ui.swing.widgets;

import studio.papercube.capturity.settings.SettingItem;

/**
 * NON-FUNCTIONAL
 * 这个接口还没有投入使用。它的作用是标记一个可以自动加载/储存值的对象。
 * <br/><br/>
 *
 *
 * <br/>
 * Created by imzhy on 2016/11/19.
 */
public interface ObjectWithPreference<T> {
    SettingItem getRelatedSettingItem();
}
