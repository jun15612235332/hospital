package testES;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 插入数据
 */
public class TestES02 {
    public static void main(String[] args) throws IOException {
        // 1.创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.106.128",9200,"http")));

        // 2.获得索引请求
        IndexRequest request = new IndexRequest(
                "book", // 索引名 - 数据库名
                "novel", // 类型名 - 表名
                "3");    // 主键     - 可以不设置,自动生成
        // 3.1  准备插入的文档 - 数据 - 这里使用json - 有三种方式
        String json = "{\"title\":\"<阿波罗游记>\",\"content\":\"记录生活点滴\"}";
        // 3.2 插入数据
        request.source(json, XContentType.JSON);

        // 4发送请求
        IndexResponse response = client.index(request);
        System.out.println(response);

        // 5.判断是否插入成功
        if (response.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("插入成功");
        }
        // 6.关闭客户端
        client.close();
    }
}
