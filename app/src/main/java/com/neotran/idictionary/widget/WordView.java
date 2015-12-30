package com.neotran.idictionary.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neotran.idictionary.R;

import org.w3c.dom.Text;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WordView extends LinearLayout {
    private Activity mActivity;
    private TextView mTitleTextView;
    private WebView mWordWebView;
    private String mWord;

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_word_view, this, true);
    }

    public void initiate(Activity target) {
        mActivity = target;
        mTitleTextView = (TextView) this.findViewById(R.id.word_title_text_view);
        mWordWebView = (WebView) this.findViewById(R.id.description_web_view);
        mWordWebView.getSettings().setJavaScriptEnabled(true);
        try {
            mWordWebView.setWebViewClient(new WordViewWebClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLayout(String word) {
        mWord = word;
        String description = "<h1><span class=\"tl\" style=\"color: rgb(51, 153, 204); font-weight: bold; font-size: 12px; font-family: tahoma, Arial; padding-left: 2px; padding-right: 2px; text-transform: uppercase; line-height: normal;\">DANH TỪ</span></h1>\n" +
                "\n" +
                "<ul style=\"color: rgb(0, 0, 0); font-family: tahoma, arial; line-height: normal;\">\n" +
                "\t<li style=\"list-style: none;\">l&ograve;ng y&ecirc;u, t&igrave;nh thương\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">love of ones country:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;l&ograve;ng y&ecirc;u nước</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">a mothers love for her children:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;t&igrave;nh mẹ y&ecirc;u con</span></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">t&igrave;nh y&ecirc;u, mối t&igrave;nh, &aacute;i t&igrave;nh\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">first love:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;mối t&igrave;nh đầu</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">never trifle with love:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;kh&ocirc;ng n&ecirc;n đ&ugrave;a bỡn với t&igrave;nh y&ecirc;u</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to be in love with:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;y&ecirc;u (ai)</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to fall in love with:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;đ&acirc;m ra y&ecirc;u (phải l&ograve;ng) (ai)</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to make love to someone:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;t&aacute;n tỉnh ai, tỏ t&igrave;nh với ai; &ocirc;m ấp h&ocirc;n h&iacute;t ai, ăn nằm với ai</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to marry for love:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;kết h&ocirc;n v&igrave; t&igrave;nh</span></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">người y&ecirc;u, người t&igrave;nh</li>\n" +
                "\t<li style=\"list-style: none;\">thần &aacute;i t&igrave;nh</li>\n" +
                "\t<li style=\"list-style: none;\">(th&ocirc;ng tục) người đ&aacute;ng y&ecirc;u; vật đ&aacute;ng y&ecirc;u</li>\n" +
                "\t<li style=\"list-style: none;\">(thể dục,thể thao) điểm kh&ocirc;ng, kh&ocirc;ng (quần vợt)\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">love all:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;kh&ocirc;ng kh&ocirc;ng (hai b&ecirc;n c&ugrave;ng kh&ocirc;ng được điểm n&agrave;o)</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">love forty:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;kh&ocirc;ng bốn mươi</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">a love set:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;một v&aacute;n thua trắng (người thua kh&ocirc;ng được điểm n&agrave;o)</span></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<p><span class=\"tl\" style=\"color: rgb(51, 153, 204); font-weight: bold; font-size: 12px; font-family: tahoma, Arial; padding-left: 2px; padding-right: 2px; text-transform: uppercase; line-height: normal;\">IDIOMS</span></p>\n" +
                "\n" +
                "<ul style=\"color: rgb(0, 0, 0); font-family: tahoma, arial; line-height: normal;\">\n" +
                "\t<li style=\"list-style: none;\">to love in a cottage\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\">&aacute;i t&igrave;nh v&agrave; nước l&atilde;</li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">one cant get it for love or money\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\">kh&ocirc;ng c&oacute; c&aacute;ch g&igrave; lấy được c&aacute;i đ&oacute;</li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">to play for love\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\">chơi v&igrave; th&iacute;ch kh&ocirc;ng phải v&igrave; tiền</li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">there is no love lost between them\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\">ch&uacute;ng n&oacute; gh&eacute;t nhau như đ&agrave;o đất đổ đi</li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "\n" +
                "<p><span class=\"tl\" style=\"color: rgb(51, 153, 204); font-weight: bold; font-size: 12px; font-family: tahoma, Arial; padding-left: 2px; padding-right: 2px; text-transform: uppercase; line-height: normal;\">NGOẠI ĐỘNG TỪ</span></p>\n" +
                "\n" +
                "<ul style=\"color: rgb(0, 0, 0); font-family: tahoma, arial; line-height: normal;\">\n" +
                "\t<li style=\"list-style: none;\">y&ecirc;u, thương, y&ecirc;u mến\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to love one another:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;y&ecirc;u nhau, thương nhau</span></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "\t<li style=\"list-style: none;\">th&iacute;ch, ưa th&iacute;ch\n" +
                "\t<ul>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to love sports:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;th&iacute;ch thể thao</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">to love music:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;th&iacute;ch &acirc;m nhạc</span></li>\n" +
                "\t\t<li style=\"list-style: none;\"><span class=\"ec\" style=\"color: rgb(0, 51, 102); font-size: 15px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">he loves to be praised:</span><span class=\"exm\" style=\"font-size: 15px; padding-left: 0px; font-family: 'Times New Roman', Times, serif; font-style: italic;\">&nbsp;n&oacute; th&iacute;ch được khen</span></li>\n" +
                "\t</ul>\n" +
                "\t</li>\n" +
                "</ul>\n";
        mTitleTextView.setText(word);
        mWordWebView.loadDataWithBaseURL("", description, "text/html", "UTF-8", "");
    }

    private class WordViewWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
