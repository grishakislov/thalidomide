package com.codekittens.thalidomide.client;

import com.codekittens.thalidomide.client.extractor.ExtractorFactory;
import com.codekittens.thalidomide.model.KarmaResponse;
import com.codekittens.thalidomide.model.ServerResponse;
import com.codekittens.thalidomide.model.VoteResponse;
import com.codekittens.thalidomide.model.WrappedVoteResponse;
import com.codekittens.thalidomide.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClientImpl implements Client {

    private static final Logger LOG = LoggerFactory.getLogger(ClientImpl.class);

    private ClientCookie cookie;
    private Settings settings;

    private static final String COMMENTS_URL = "https://leprosorium.ru/users/grishakislov/comments/";
    private static final String COMMENTS_PAGE_POSTFIX = "pages/";

    private static final String KARMA_URL = "https://leprosorium.ru/ajax/user/karma/list/";
    private static final String INBOX_URL = "https://leprosorium.ru/ajax/inbox/moar/";

    private static final String VOTES_LIST_URL = "https://leprosorium.ru/ajax/vote/list/";
    private static final int INBOX_OFFSET = 42;
    private static final String PROFILE_URL = "https://leprosorium.ru/users/grishakislov";

    public ClientImpl() {
        cookie = new ClientCookie("19201", "5fcf1947c5f031783df86f100712c5f2", "GA1.2.1440980545.1407575361");
        settings = Settings.createClientSettings();
    }

    @Override
    public void init() throws ClientException {
        HttpsURLConnection connection = getConnection(PROFILE_URL);
        LOG.debug("Getting CSRF token...");
        String token = ExtractorFactory.createCsrfTokenExtractor().extractOne(connection);
        settings.setCsrfToken(token);
        LOG.debug("CSRF token loaded");
    }

    @Override
    public void login() throws ClientException {
        //TODO: Implement (cookie sid)
    }

    @Override
    public ServerResponse editControls() throws ClientException {
        /*
        https://music.leprosorium.ru/ajax/controls/edit/
        formdata: logo=012345&csrf_token=
        response: {"status": "OK", "logo": "http://std3.ru/34/4c/1410355154-344c27b542e6b6d2e91c62d03b7f8a49.gif"}
         */
        return null;
    }

    @Override
    public ServerResponse uploadMedia(String media) throws ClientException {
        /*
        https://music.leprosorium.ru/ajax/media/
        response: {"status": "OK", "media_id": 012345}
         */
        return null;
    }

    @Override
    public ServerResponse listComments() throws ClientException {
        return null;
    }

    @Override
    public VoteResponse listCommentVotes(String commentId) throws ClientException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<WrappedVoteResponse> listCommentVotes(List<String> commentIds) throws ClientException {
        LOG.debug("Listing votes for {} comments", commentIds.size());
        Parser<VoteResponse> parser = new Parser();
        List<WrappedVoteResponse> votes = new ArrayList<>();

        commentIds.forEach((String commentId) -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            VoteResponse currentVote;
            WrappedVoteResponse wrappedVote;
            HttpsURLConnection connection = getConnection(VOTES_LIST_URL);

            try {
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                FormData formData = new FormData();

                formData.addProperty("comment", commentId);
                formData.addProperty("csrf_token", settings.getCsrfToken());
                formData.addProperty("limit", 100);
                formData.addProperty("offset", 0);

                out.write(formData.toString());

                out.close();
                String resp = readInputStream(connection);

                currentVote = parser.parse(VoteResponse.class, resp);
                wrappedVote = new WrappedVoteResponse(commentId, currentVote);
                votes.add(wrappedVote);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        return votes;
    }

    @Override
    public VoteResponse listPostVotes(String postId) throws ClientException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<VoteResponse> listPostVotes(List<String> postId) throws ClientException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> listCommentsIds() throws ClientException {
        LOG.debug("Listing comments...");
        List<String> result = new ArrayList<>();
        HttpsURLConnection connection;
        int pageNo = 0;
        int totalPages = numCommentsPages();
        LOG.debug("Listing {} pages total", totalPages);

        while (pageNo < 3) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String url = getCommentsPageUrl(pageNo);
            LOG.debug("Current url: {}", url);
            connection = getConnection(url);
            List<String> ids = ExtractorFactory.createCommentsIdsExtractor().extractList(connection);
            if (ids != null && !ids.isEmpty()) {
                result.addAll(ids);
            } else {
                return result;
            }
            pageNo++;
        }
        return result;
    }

    private int numCommentsPages() {
        HttpsURLConnection connection = getConnection(COMMENTS_URL);
        String numPages = ExtractorFactory.createNumPaginatorPagesExtractor().extractOne(connection);
        return Integer.parseInt(numPages);
    }

    private String getCommentsPageUrl(int pageNo) {
        if (pageNo == 0) {
            return COMMENTS_URL;
        } else {
            return COMMENTS_URL + COMMENTS_PAGE_POSTFIX + Integer.toString(pageNo + 1);
        }
    }

    @Override
    public KarmaResponse listKarma(int limit, int offset) throws ClientException {
        LOG.debug("Listing karma for user {}", settings.getUid());
        try {
            HttpsURLConnection connection = getConnection(KARMA_URL);

            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            FormData formData = new FormData();
            formData.addProperty("user", settings.getUid());
            formData.addProperty("limit", limit);
            formData.addProperty("offset", offset);
            formData.addProperty("csrf_token", settings.getCsrfToken());
            out.write(formData.toString());

            out.close();
            String resp = readInputStream(connection);

            Parser<KarmaResponse> parser = new Parser<>();

            return parser.parse(KarmaResponse.class, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readInputStream(HttpsURLConnection connection) {
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line = "";
            String newLine;
            while ((newLine = rd.readLine()) != null) {
                line += "\n" + newLine;
            }
            rd.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpsURLConnection getConnection(String urlString) {
        try {

            URL url = new URL(urlString);
            HttpsURLConnection result = (HttpsURLConnection) url.openConnection();

            result.setRequestProperty("Cookie", cookie.toString());
            result.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            result.setDoOutput(true);
            result.setDoInput(true);
            result.connect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
