package com.lsk.mc.myplugin;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHelper {
    private JedisPool pool;
    private static final Gson gson = new Gson();

    public RedisHelper(String host, int port, String password, int db) {
        this.pool = new JedisPool(new JedisPoolConfig(), host, port, 4000, password, db);
    }

    public void storeDeathInfo(String playerUUID, DeathInfo deathInfo) {
        try(Jedis jedis = pool.getResource()) {
            String jsonDeathInfo = gson.toJson(deathInfo);
            jedis.set(playerUUID + "-LAST-DEATH", jsonDeathInfo);
            jedis.expire(playerUUID + "-LAST-DEATH", 86400);
        }
    }

    public DeathInfo getDeathInfo(String playerUUID) {
        try (Jedis jedis = pool.getResource()) {
            if (! jedis.exists(playerUUID + "-LAST-DEATH")) {
                return null;
            }
            String jsonDeathInfo = jedis.get(playerUUID + "-LAST-DEATH");
            return gson.fromJson(jsonDeathInfo, DeathInfo.class);
        }
    }

    public void close() {
        pool.close();
    }
}
