package com.mailchimp;

import com.mailchimp.domain.*;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

/**
 * @author stevensnoeijen, eamoralesl
 */
public interface MailChimpClient {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * API Root
     *
     * @return
     */
    @RequestLine("GET /3.0/")
    Root getRoot();

    @RequestLine("GET /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member getListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    @RequestLine("POST /3.0/lists/{list-id}/members")
    Member createListMember(@Param("list-id") String listId, Member member);

    @RequestLine("PUT /3.0/lists/{list-id}/members/{subscriber-hash}")
    Member updateListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash, Member member);

    @RequestLine("DELETE /3.0/lists/{list-id}/members/{subscriber-hash}")
    void removeListMember(@Param("list-id") String listId, @Param("subscriber-hash") String subscriberHash);

    /**
     * Create a new list in your MailChimp account.
     *
     * @param list
     *
     * @return created list
     */
    @RequestLine("POST /3.0/lists")
    SubscriberList createSubscriberList(SubscriberList list);

    @RequestLine("DELETE /3.0/lists/{list-id}")
    void removeSubscriberList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}")
    SubscriberList getSubscriberList(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists?offset={offset}&count={count}")
    SubscriberLists getSubscriberLists(@Param("offset") Integer offset, @Param("count") Integer count);

    @RequestLine("GET /3.0/lists/{list-id}/members")
    Members getListMembers(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/members")
    Members getListMembers(@Param("list-id") String listId, @QueryMap Page page);

    @RequestLine("GET /3.0/lists/{list-id}/members?status={status}")
    Members getListMembersByStatus(@Param("list-id") String listId, @Param(value = "status", expander = StatusToLower.class) SubscribeStatus status, @QueryMap Page page);

    @RequestLine("GET /3.0/lists/{list-id}/merge-fields")
    ListMergeFields getListMergeFields(@Param("list-id") String listId);

    @RequestLine("POST /3.0/lists/{list-id}/merge-fields")
    ListMergeField createMergeField(@Param("list-id") String listId, ListMergeField mergeField);

    @RequestLine("DELETE /lists/{list-id}/merge-fields/{merge-id}")
    void removeListMergeField(@Param("list-id") String listId, @Param("merge-id") String mergeId);

    @RequestLine("POST /3.0/lists/{list-id}/segments")
    Segment createSegment(@Param("list-id") String listId, SegmentCreate request);

    @RequestLine("POST /3.0/lists/{list-id}/segments/{segment-id}")
    SegmentModified modifySegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId, SegmentModify request);

    @RequestLine("GET /3.0/lists/{list-id}/segments")
    Segments getSegments(@Param("list-id") String listId);

    @RequestLine("GET /3.0/lists/{list-id}/segments/{segment-id}")
    Segment getSegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId);

    @RequestLine("DELETE /3.0/lists/{list-id}/segments/{segment-id}")
    void removeSegment(@Param("list-id") String listId, @Param("segment-id") Integer segmentId);

    @RequestLine("POST /3.0/batches")
    Batch createBatch(CreateBatch batch);

    @RequestLine("GET /3.0/batches/{batch-id}")
    Batch getBatch(@Param("batch-id") String batchId);

    @RequestLine("GET /3.0/batches")
    Batches getBatches(@QueryMap Page page);

    @RequestLine("DELETE /3.0/batches/{batch-id}")
    void removeBatch(@Param("batch-id") String batchId);

    @RequestLine("GET /3.0/search-members?query={query}")
    SearchMembers searchMembers(@Param("query") String query);

    @RequestLine("GET /3.0/search-members?query={query}&list_id={listId}")
    SearchMembers searchMembers(@Param("query") String query, @Param("listId") String listId);

    static MailChimpClientBuilder builder(){
        return new MailChimpClientBuilder();
    }
}
