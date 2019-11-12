package org.myprm.com.app.exp.vo;

import androidx.room.Entity;
import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rakesh
 */
@Entity(primaryKeys = "contactId")
@IgnoreExtraProperties
public class MyContact {


    @SerializedName("contactId")
    @NonNull
    private String contactId;

    @SerializedName("contactName")
    @NonNull
    private String contactName;

    @SerializedName("activityTag")
    private String activityTag;

    @SerializedName("followUpSpan")
    private String followUpSpan;

    @SerializedName("nextFollowUpDate")
    private String nextFollowUpDate;

    @SerializedName("followUpNotesId")
    private String followUpNotesId;

    @NonNull
    public String getContactId() {
        return contactId;
    }

    public void setContactId(@NonNull String contactId) {
        this.contactId = contactId;
    }

    @NonNull
    public String getContactName() {
        return contactName;
    }

    public void setContactName(@NonNull String contactName) {
        this.contactName = contactName;
    }

    public String getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag;
    }

    public String getFollowUpSpan() {
        return followUpSpan;
    }

    public void setFollowUpSpan(String followUpSpan) {
        this.followUpSpan = followUpSpan;
    }

    public String getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(String nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
    }

    public String getFollowUpNotesId() {
        return followUpNotesId;
    }

    public void setFollowUpNotesId(String followUpNotesId) {
        this.followUpNotesId = followUpNotesId;
    }

    @Override
    public String toString() {
        return "MyContact{" +
                "contactId='" + contactId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", activityTag='" + activityTag + '\'' +
                ", followUpSpan='" + followUpSpan + '\'' +
                ", nextFollowUpDate='" + nextFollowUpDate + '\'' +
                ", followUpNotesId='" + followUpNotesId + '\'' +
                '}';
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("contactId", getContactId());
        result.put("contactName", getContactName());
        result.put("activityTag", getActivityTag());
        result.put("followUpSpan", getFollowUpSpan());
        result.put("nextFollowUpDate", getNextFollowUpDate());
        result.put("followUpNotesId", getFollowUpNotesId());

        return result;
    }
}