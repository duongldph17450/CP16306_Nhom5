package com.example.cp16306_nhom5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cp16306_nhom5.model.Questions;
import com.example.cp16306_nhom5.database.QuizContract.*;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GoQuiz.db";
    private static final int DATBASE_VERSION = 3;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATBASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionTable.COLUMN_LEVELS_ID + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    private void fillQuestionsTable()
    {
        // ALL
        Questions q1 = new Questions("Bao lâu nữa bán được 1 tỷ gói mè?","1 năm","2 tháng","6 ngày","Em bán kem đánh răng",4,
                Questions.CATEGORY_ALL, Questions.LEVEL1);
        addQuestions(q1);

        Questions q2 = new Questions("Loại củ quả nào sau đây là biểu tượng của ngày lễ Halloween?","Bắp cải","Dưa leo","Bí ngô","Cà rốt", 3,
                Questions.CATEGORY_ALL, Questions.LEVEL1);
        addQuestions(q2);

        Questions qa3 = new Questions("Loại trái cây được sử dụng phổ biến nhất để làm rượu?","Nho","Cam","Việt quất","Dâu tây", 1,
                Questions.CATEGORY_ALL, Questions.LEVEL1);
        addQuestions(qa3);

        Questions qa2 = new Questions("Cung hoàng đạo là gì?","Giải mã giấc mơ","Bói toán","Tiên tri","Sự giải thích vị trí của các ngôi sao", 4,
                Questions.CATEGORY_ALL, Questions.LEVEL2);
        addQuestions(qa2);

        Questions qa4 = new Questions("Đâu là thức ăn tự nhiên cho ngựa?","Trái cây","Thịt","Cỏ","Đường", 3,
                Questions.CATEGORY_ALL, Questions.LEVEL2);
        addQuestions(qa4);

        Questions qa5 = new Questions("Những chú hề biểu diễn ở đâu?","Nhà máy","Bệnh viện","Cửa hàng","Rạp xiếc", 4,
                Questions.CATEGORY_ALL, Questions.LEVEL2);
        addQuestions(qa5);

        Questions qa1 = new Questions("Fenrir là ai?","Người viết biên niên sử châu Âu","Một con sói tàn ác trong thần thoại Bắc Âu","Người khám phá ra Nam Mỹ","Hoàng đế La Mã", 2,
                Questions.CATEGORY_ALL, Questions.LEVEL3);
        addQuestions(qa1);

        Questions qa6 = new Questions("\"Nơi bí mật\" mà Frances Hodgson Burnett đã viết là về cái gì?","Khu vườn","Hành lang","Căn phòng","Sân nhà", 1,
                Questions.CATEGORY_ALL, Questions.LEVEL3);
        addQuestions(qa6);

        Questions qa7 = new Questions("Có bao nhiêu cung hoàng đạo?","8","10","9","12", 4,
                Questions.CATEGORY_ALL, Questions.LEVEL3);
        addQuestions(qa7);


        //GEOGRAPHY
        Questions q3 = new Questions("Từ \"Sakura\" trong tiếng Nhật có nghĩa là gì?","Hoa táo","Hoa anh đào","Hoa giấy","Hoa đào",2,
                Questions.CATEGORY_GEOGRAPHY, Questions.LEVEL1);
        addQuestions(q3);

        Questions qg1 = new Questions("Đất nước nào nổi tiếng với những kim tự thác","Nigeria","Angola","Ai Cập","Mexico",3,
                Questions.CATEGORY_GEOGRAPHY, Questions.LEVEL1);
        addQuestions(qg1);

        Questions qg2 = new Questions("Trên quốc kỳ nước Liban có hình loài cây nào?","Cây táo","Cây xoài","Cây ổi","Cây tuyết tùng",4,
                Questions.CATEGORY_GEOGRAPHY, Questions.LEVEL3);
        addQuestions(qg2);

        Questions qg3 = new Questions("Quốc gia có diện tích lớn nhất thế giới?","Trung Quốc","Hoa Kỳ","Liên bang Nga","Canada", 3,
                Questions.CATEGORY_GEOGRAPHY, Questions.LEVEL2);
        addQuestions(qg3);


        //HISTORY
        Questions q4 = new Questions("Kim tự thác Kheops được xây dựng trong bao nhiêu năm?","Hơn 1 năm","Hơn 20 năm","5 năm","Hơn 8 năm", 2,
                Questions.CATEGORY_HISTORY, Questions.LEVEL1);
        addQuestions(q4);

        Questions qh1 = new Questions("Cuộc chiến tranh lớn nào đã kết thúc vào năm 1975","Chiến tranh Việt Nam","Nội chiến Guatemalan","Chiến tranh bụi rậm Rhodesi","Nội chiến Mozambique", 1,
                Questions.CATEGORY_HISTORY, Questions.LEVEL2);
        addQuestions(qh1);

        Questions qh2 = new Questions("Anh em nhà Wright đã chế tạo ra thứ gì?","Đầu máy hơi nước","Máy bay cất cánh thành công đầu tiên","Lò vi sóng","Xe máy", 2,
                Questions.CATEGORY_HISTORY, Questions.LEVEL3);
        addQuestions(qh2);

        // SCIENCE
        Questions q5 = new Questions("Tên thường gọi của Sodium Chloride là gì?","Muối","Giấm","Đường","Chất tẩy trắng", 1,
                Questions.CATEGORY_SCIENCE, Questions.LEVEL3);
        addQuestions(q5);

        Questions q6 = new Questions("Trộn màu xanh da trời với màu đỏ sẽ được màu gì?","Xanh lá","Xanh da trời","Tím","Nâu", 3,
                Questions.CATEGORY_SCIENCE, Questions.LEVEL2);
        addQuestions(q6);

        Questions qs1 = new Questions("Nước sôi ở nhiệt đô bao nhiêu?","100 độ C","80 độ C","120 độ C","50 độ C", 1,
                Questions.CATEGORY_SCIENCE, Questions.LEVEL1);
        addQuestions(qs1);


        // MATH
        Questions q7 = new Questions("5 + 5 = ?","5","10","15","20",2,
                Questions.CATEGORY_MATHS, Questions.LEVEL1);
        addQuestions(q7);

        Questions q8 = new Questions("30 - 7","13","3","17","23",4,
                Questions.CATEGORY_MATHS, Questions.LEVEL2);
        addQuestions(q8);

        Questions qm1 = new Questions("60 / 3","20","15","2","5",1,
                Questions.CATEGORY_MATHS, Questions.LEVEL3);
        addQuestions(qm1);

        // ENGLISH
        Questions q9= new Questions("Can you swim?","In a pool","Yes, I can","Very good","I am fine",2,
                Questions.CATEGORY_ENGLISH, Questions.LEVEL1);
        addQuestions(q9);

        Questions q10 = new Questions("Did he go to work or to school?","To work","No, he doesn't","At 3:00 PM","Every day",1,
                Questions.CATEGORY_ENGLISH, Questions.LEVEL2);
        addQuestions(q10);

        Questions qe1 = new Questions("Have you done the laundry?","Yes, I do","No, he doesn't","On Wednesdays","No, I haven't",4,
                Questions.CATEGORY_ENGLISH, Questions.LEVEL3);
        addQuestions(qe1);

    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY,question.getCategory());
        cv.put(QuestionTable.COLUMN_LEVELS_ID,question.getLevels());

        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    public ArrayList<Questions> getAllQuestionsWithCategoryAndLevels(int levelsID, String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY,
                QuestionTable.COLUMN_LEVELS_ID
        };

        String selection = QuestionTable.COLUMN_LEVELS_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selecionArgs[] = {String.valueOf(levelsID), category};

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selecionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));
                question.setLevels(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_LEVELS_ID)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }


    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };



        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

    public ArrayList<Questions> getAllQuestionsWithCategory(String category) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String selecionArgs[] = {category};

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selecionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }

}
