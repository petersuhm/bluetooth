package financeAccessor;

import java.util.LinkedList;
import java.util.List;

import model.Product;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
/**
 * Simulates a Microsoft .NET WebAPI
 * @author Christian
 *
 */
public class WebAPI extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "StoreManagerDB";
 
    public WebAPI(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE product ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "hex varchar(255), "+
                "productname varchar(255), " + 
                "sku varchar(255));";
 
        // create books table
        db.execSQL(CREATE_PRODUCT_TABLE);
        
        final String INSERTION = "INSERT INTO product VALUES(null, 'aAdk309j', 'redningsvest', '');";
        
        db.execSQL(INSERTION);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS product");
 
        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------
 
    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
 
    // Books table name
    private static final String TABLE_PRODUCT = "product";
 
    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_HEX = "hex";
    private static final String KEY_PRODUCTNAME = "productname";
 
    private static final String[] COLUMNS = {KEY_ID, KEY_HEX, KEY_PRODUCTNAME};
 
//    public void addBook(Product product){
//        Log.d("addBook", product.toString());
//        // 1. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        // 2. create ContentValues to add key "column"/value
//        ContentValues values = new ContentValues();
//        values.put(KEY_TITLE, product.getTitle()); // get title 
//        values.put(KEY_AUTHOR, product.getAuthor()); // get author
// 
//        // 3. insert
//        db.insert(TABLE_BOOKS, // table
//                null, //nullColumnHack
//                values); // key/value -> keys = column names/ values = column values
// 
//        // 4. close
//        db.close(); 
//    }
 
    public Product getProduct(String hex){
 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_PRODUCT, // a. table
                COLUMNS, // b. column names
                " hex = ?", // c. selections 
                new String[] { String.valueOf(hex) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build book object
        Product product = new Product(cursor.getString(0),cursor.getString(1), cursor.getString(2));
        // 5. return book
        return product;
    }
 
    // Get All Product
    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<Product>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PRODUCT;
 
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        Product book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Product(cursor.getString(0),cursor.getString(1), cursor.getString(2));
 
                // Add book to books
                products.add(book);
            } while (cursor.moveToNext());
        }
 
        // return books
        return products;
    }
    
    
    public int getProductCount(int hex)
    {
    	String query = "SELECT count(*) as total from product where hex = ?";
    	  // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(hex)});
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build book object
        int count = Integer.parseInt(cursor.getString(0));
        // 5. return result
        return count;
    }
    
// 
//     // Updating single book
//    public int updateBook(Book book) {
// 
//        // 1. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        // 2. create ContentValues to add key "column"/value
//        ContentValues values = new ContentValues();
//        values.put("title", book.getTitle()); // get title 
//        values.put("author", book.getAuthor()); // get author
// 
//        // 3. updating row
//        int i = db.update(TABLE_BOOKS, //table
//                values, // column/value
//                KEY_ID+" = ?", // selections
//                new String[] { String.valueOf(book.getId()) }); //selection args
// 
//        // 4. close
//        db.close();
// 
//        return i;
// 
//    }
// 
//    // Deleting single book
//    public void deleteBook(Book book) {
// 
//        // 1. get reference to writable DB
//        SQLiteDatabase db = this.getWritableDatabase();
// 
//        // 2. delete
//        db.delete(TABLE_BOOKS,
//                KEY_ID+" = ?",
//                new String[] { String.valueOf(book.getId()) });
// 
//        // 3. close
//        db.close();
// 
//        Log.d("deleteBook", book.toString());
// 
//    }
}