--- Lua脚本语言，释放锁 比较value是否相等，避免删除别人占有的锁
if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
