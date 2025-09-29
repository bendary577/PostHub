import { onRequest } from "firebase-functions/v2/https";
import { defineSecret } from "firebase-functions/params";
import axios from "axios";

// Define secrets
const APP_ID = defineSecret("FACEBOOK_APP_ID");
const APP_SECRET = defineSecret("FACEBOOK_APP_SECRET");

export const getPublicPagePosts = onRequest(
  { secrets: [APP_ID, APP_SECRET] },  
  async (req, res) => {
    const pageId = req.query.pageId as string;
    if (!pageId) {
      res.status(400).send("Missing pageId parameter");
      return; 
    }

    const since = Math.floor((Date.now() - 24 * 60 * 60 * 1000) / 1000);

    try {
      // Use the secret values
      const tokenResponse = await axios.get(
        "https://graph.facebook.com/oauth/access_token",
        {
          params: {
            client_id: APP_ID.value(),
            client_secret: APP_SECRET.value(),
            grant_type: "client_credentials",
          },
        }
      );

      const appToken: string = tokenResponse.data.access_token;

      const postsResponse = await axios.get(
        `https://graph.facebook.com/v16.0/${pageId}/posts`,
        {
          params: {
            access_token: appToken,
            since,
            fields: "id,message,created_time,permalink_url",
          },
        }
      );

      res.json(postsResponse.data);
    } catch (err: any) {
      console.error(err.response?.data || err);
      res.status(500).send("Error fetching posts");
    }
  }
);
