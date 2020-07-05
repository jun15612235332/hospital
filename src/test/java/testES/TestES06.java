package testES;

import com.alibaba.fastjson.JSONObject;
import com.zhiyou100.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量插入数据
 */
public class TestES06 {
    public static void main(String[] args) throws IOException {
        /**
         * 模拟从数据库查出只有两个字段的数据
         */
        List<User> users = new ArrayList<>();
        users.add(new User("老三", "123456"));
        users.add(new User("三", "123456"));
        users.add(new User("老五", "123456"));
        users.add(new User("我是老三", "123456"));
        List<String> userJson = new ArrayList<>();
        for (User user : users) {
            userJson.add(JSONObject.toJSONString(user));
        }
        System.out.println(userJson);

        // 0 .创建客户端
        RestHighLevelClient cilent = new RestHighLevelClient(RestClient.builder( // 端口不是字符串是数字
                new HttpHost("192.168.106.128", 9200, "http")));// ip 端口 请求协议

        // 1.创建批量请求
        BulkRequest request = new BulkRequest();

        // 2. 创建添加请求[索引,类型,id] 写json字符串  将json设置进请求
        for(int i = 0;i < userJson.size();i++){
            request.add(new IndexRequest("ssm","user").
                    source(userJson.get(i), XContentType.JSON));
        }
        // 3. 发出请求

        BulkResponse response = cilent.bulk(request);
        for (BulkItemResponse bulkItemResponse : response) {
            if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                    || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                System.out.println("成功");
            }
        }

        // 6.关闭客户端
        cilent.close();
    }
}
