package testES;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

/**
 * 获得指定数据
 */
public class TestES04 {
    public static void main(String[] args) throws IOException {
        // 1.创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.106.128",9200,"http")));

        // 2.获得[查询]索引请求
        GetRequest request = new GetRequest(
                "book", // 索引名 - 数据库名
                "novel", // 类型名 - 表名
                "1");    // 主键     - 查询的主键

        //选择返回的字段
        String[] includes = new String[]{"title", "content"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);

        // 同步请求
        GetResponse getResponse = client.get(request);
        if (getResponse.isExists()) { // 文档存在
            String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
            //Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
            System.out.println(sourceAsString);
        }

        // 6.关闭客户端
        client.close();
    }
}
