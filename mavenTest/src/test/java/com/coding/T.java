/*
package com.coding;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.HashMap;


public class T {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCredential credential = MongoCredential.createCredential("root", "settingsdb", "123456".toCharArray());
        MongoDatabase db = mongoClient.getDatabase("settingsdb");
        MongoCollection<Document> collection = db.getCollection("api_setting");
        System.out.println(collection);
        Document document = new Document();
        document.append("app_code", "*");
        document.append("key", "END");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(114);
        list.add(223);
        list.add(414);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", list);
        document.append("value", map);
        collection.insertOne(document);
        FindIterable<Document> documents = collection.find();
        for (Document d : documents) {
            System.out.println(d);
        }

    }
}


    */
/*public Object querySettingsByAppCode(String appCode) {
        MongoCollection<org.bson.Document> col = collection;
        ArrayList<NAPISettingsVo> voList = new ArrayList<>();
        MongoCursor<Document> cur = null;
        if (!appCode.equals("*")) { // first * if app_code not *
            cur = col.find(Filters.eq("app_code", "*")).iterator();
            putAllIn(voList, cur);
            cur = null;
        }

        // anyway, specified app_code include "*" will be fetched
        cur = col.find(Filters.eq("app_code", appCode)).iterator();
        if (cur == null) {
            return new ArrayList<>();
        }
        putAllIn(voList, cur);

        return voList;
    }


    public List<NAPISettingsVo> queryAll() {
        Query query = new Query();
        FindIterable<NAPISettingsDO> napiSettingsDoList = collection.find( NAPISettingsDO.class);
        MongoCursor<NAPISettingsDO> iterator = napiSettingsDoList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        return null;
    }


    public NAPISettingsVo queryById(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        BsonDocument bsonDocument = new BsonDocument();

        FindIterable<NAPISettingsDO> id1 = collection.find(Filters.eq("_id", id), NAPISettingsDO.class);
        for (NAPISettingsDO d: id1) {
            System.out.println(d);
        }
        return null;
    }


    public Boolean deleteById(String id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        return collection.deleteMany(Filters.eq(id)).getDeletedCount() > 0;
    }

 *//*
*/
/*   public NAPISettingsVo updateSettings(UpdateSettingsFrom updateSettingsFrom) {
        Criteria criteria = Criteria.where("_id").is(updateSettingsFrom.getId());
        Query query = new Query(criteria);
        FindIterable<NAPISettingsDO> napiSettingsDOS = collection.find(Filters.eq(updateSettingsFrom.getId()), NAPISettingsDO.class);
        NAPISettingsDO first = napiSettingsDOS.first();
        if (!StringUtils.isEmpty(updateSettingsFrom.getAppCode())) {
            first.appCode = updateSettingsFrom.getAppCode();
        }
        if (!StringUtils.isEmpty(updateSettingsFrom.getKey())) {
            first.key = updateSettingsFrom.getKey();
        }
        if (!Objects.isNull(updateSettingsFrom.getValue())) {
            first.value = updateSettingsFrom.getValue();
        }

        collection.insertOne(first);
        return BeanMapper.map(settingsDO, NAPISettingsVo.class);
    }


    public NAPISettingsVo createSettings(CreateSettingsFrom createSettingFrom) {
        NAPISettingsDO settingDo = BeanMapper.map(createSettingFrom, NAPISettingsDO.class);
        NAPISettingsDO save = collection.save(settingDo);
        return BeanMapper.map(save, NAPISettingsVo.class);
    }*//*
*/
/*


    private void putAllIn(List<NAPISettingsVo> target, MongoCursor<org.bson.Document> cur) {

            while (cur.hasNext()) {
                org.bson.Document vvv = cur.next();
                if (vvv.containsKey("key") && vvv.containsKey("value")) {
                    NAPISettingsVo vo = NAPISettingsVo.builder()
                            .id(vvv.get("_id").toString())
                            .value(vvv.getString("app_code"))
                            .key(vvv.getString("key"))
                            .value(ensurePrimitive(vvv.get("value")))
                            .build();
                    target.add(vo);
                }
            }

    }

    public Object ensurePrimitive(Object obj) {
        Object ret = obj;
        if (obj == null) {
            return ret;
        }
        if (obj instanceof Number || obj instanceof String) {
            return obj;
        }
        String src = obj.toString();
        if (src.startsWith("[")) {
            try {
                ret = JsonTools.fromJson(obj.toString(), ArrayList.class);
            } catch (Exception e) {
                throw new DaoException("json pattern match exception");
            }
        } else if (src.startsWith("{")) {
            try {
                ret = JsonTools.fromJson(obj.toString(), HashMap.class);
            } catch (Exception e) {
            }
        }
        return ret;
    }
}

@org.springframework.data.mongodb.core.mapping.Document(collection = "collection")
class NAPISettingsDO {
    @Id
    String id;

    @Field("app_code")
    String appCode;

    @Field("key")
    String key;

    @Field("value")
    Object value;
}


@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class NAPISettingsVo implements Serializable {

    private static final long serialVersionUID = 191240155903060L;

    private String id;

    private String appCode;

    private String key;

    private Object value;
}


@Data
 class CreateSettingsFrom {

    private String appCode;

    private String key;

    private Object value;

    public Boolean check() {
        if (StringUtils.isEmpty(appCode) || StringUtils.isEmpty(key) || Objects.isNull(value)) {
            return false;
        }
        return true;
    }
}


@Data
 class UpdateSettingsFrom {

    private String id;

    private String appCode;

    private String key;

    private Object value;

    public Boolean check() {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(appCode)
                || StringUtils.isEmpty(key) || Objects.isNull(value)) {
            return false;
        }
        return true;
    }
}



 class BeanMapper {
    public static <S, D> D map(S source, Class<D> clazz) {
        D target = null;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <S, D> List<D> map(*//*
*/
/*集合类型*//*
*/
/*Iterable<S> sources, Class<D> clazz) {
        List<D> list = new ArrayList<>();
        for (S source: sources) {
            if (!Objects.isNull(source)) {
                list.add(map(source, clazz));
            }
        }
        return list;
    }
}

class JsonTools {

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return jsonString == null ? null : getMapper().fromJson(jsonString, clazz);
    }
*/
