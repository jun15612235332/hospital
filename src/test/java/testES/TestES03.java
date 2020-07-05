package testES;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 更新数据
 */
public class TestES03 {
    public static void main(String[] args) throws IOException {
        // 1.创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.106.128",9200,"http")));

        // 2. 获得更新请求 参1:索引 参2:类型 参3:id
        UpdateRequest request = new UpdateRequest("book", "novel", "1");
        // 3. 设置更新的内容
        request.doc(jsonBuilder()
                .startObject()
                .field("title", "儒林外史续集")
                .endObject());
        // 4.请客户端执行更新请求
        UpdateResponse updateResponse = client.update(request);
        //Result result = updateResponse.getResult();
        //System.out.println(request.toString());
        // 5.关闭客户端
        client.close();
        System.out.println("成功");
    }

}
