package testES;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * 搜索
 */
public class TestES05 {
    public static void main(String[] args) throws IOException {
        // 1.创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.106.128",9200,"http")));

        // 2.获得[查询]请求
        SearchRequest searchRequest = new SearchRequest("ssm");
        searchRequest.types("user");

        // 3、用SearchSourceBuilder来构造查询请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder =  QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("userName", "老三"));
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        // 4.将请求体加入到请求中
        searchRequest.source(sourceBuilder);
        // 5、发送请求
        SearchResponse searchResponse = client.search(searchRequest);
        // 6.处理搜索命中文档结果
        SearchHits hits = searchResponse.getHits();
        System.out.println("共"+hits.getTotalHits());
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();

            //取_source字段值
            String sourceAsString = hit.getSourceAsString(); //取成json串
            // Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 取成map对象
            System.out.println(sourceAsString);
        }
        // 6.关闭客户端
        client.close();
    }
}
