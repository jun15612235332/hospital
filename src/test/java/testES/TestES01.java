package testES;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 创建索引-->类似于创建数据库
 */
public class TestES01 {
    public static void main(String[] args) throws IOException {
        //创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.106.128", 9200, "http")));
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("ssm");
        //设置分片和副本
        request.settings(Settings.builder().
                put("index.number_of_shards", 5).
                put("index.number_of_replicas", 1));
        //构建结构化索引(mappings)->创建类型(type) -> 类似于创建表结构
        String jsonValue="{\"user\":{\"properties\":{\"userName\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\"password\":{\"type\":\"text\"}}}}";
        request.mapping("user", jsonValue, XContentType.JSON);
        //发出请求
        client.indices().create(request);
        // 关闭客户端
        client.close();
        System.out.println("成功");
    }
}
