package cn.handyplus.region.service;

import cn.handyplus.lib.db.Db;
import cn.handyplus.region.enter.Ip2regionEnter;

/**
 * 玩家IP数据显示记录
 *
 * @author handy
 */
public class Ip2regionService {
    private Ip2regionService() {
    }

    private static class SingletonHolder {
        private static final Ip2regionService INSTANCE = new Ip2regionService();
    }

    public static Ip2regionService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 新增
     *
     * @param enter 入参
     */
    public void add(Ip2regionEnter enter) {
        Db.use(Ip2regionEnter.class).execution().insert(enter);
    }

    /**
     * 根据uuid查询
     *
     * @param playerUuid 玩家uid
     * @return 显示记录
     */
    public Ip2regionEnter findByPlayerUuid(String playerUuid) {
        Db<Ip2regionEnter> use = Db.use(Ip2regionEnter.class);
        use.where().eq(Ip2regionEnter::getPlayerUuid, playerUuid);
        return use.execution().selectOne();
    }

    /**
     * 根据UUID更新
     *
     * @param playerUuid 玩家uid
     * @param showEnable 是否显示
     */
    public void update(String playerUuid, Boolean showEnable) {
        Db<Ip2regionEnter> use = Db.use(Ip2regionEnter.class);
        use.update().set(Ip2regionEnter::getShowEnable, showEnable);
        use.where().eq(Ip2regionEnter::getPlayerUuid, playerUuid);
        use.execution().update();
    }

}