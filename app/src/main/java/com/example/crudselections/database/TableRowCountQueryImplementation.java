package com.example.crudselections.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudselections.model.TableRowCount;

import static com.example.crudselections.util.Constants.*;

public class TableRowCountQueryImplementation implements QueryContract.TableRowCountQuery {

    @Override
    public void getTableRowCount(QueryResponse<TableRowCount> response) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            long studentCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_STUDENT);
            long subjectCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_SUBJECT);
            long takenSubjectCount = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_STUDENT_SUBJECT);

            TableRowCount tableRowCount = new TableRowCount(studentCount, subjectCount, takenSubjectCount);
            response.onSuccess(tableRowCount);

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }
}
