package com.proofpoint.dbpool;

import com.proofpoint.configuration.Config;

/**
 * Cofiguration for (@see H2EmbeddedDataSource}.
 * </p>
 * The configuration options can be chained as follows:<br>
 * {@code
 *     H2EmbeddedDataSourceConfig config = new H2EmbeddedDataSourceConfig()
 *             .setUsername("username")
 *             .setPassword("password")
 *             .setMaxConnections(20)
 *             .setMaxConnectionWait(new Duration(20, TimeUnit.MILLISECONDS)),
 *             .setFilename(fileName)
 *             .setInitScript("src/test/db/h2.ddl");
 * }
 */
public class H2EmbeddedDataSourceConfig extends ManagedDataSourceConfig<H2EmbeddedDataSourceConfig>
{
    private String filename;
    private String initScript;
    private String allowLiterals = "ALL";
    private String compressLob = "LZF";
    private int cacheSize = 16384;
    private long maxLengthInplaceLob = 1024;
    private long maxMemoryRows = 10000;

    public H2EmbeddedDataSourceConfig()
    {
        setUsername("sa");
        setPassword("");
    }

    /**
     * Databse filename
     */
    public String getFilename()
    {
        return filename;
    }

    @Config("db.filename")
    public H2EmbeddedDataSourceConfig setFilename(String filename)
    {
        this.filename = filename;
        return this;
    }

    /**
     * Initialization script run at startup
     */
    public String getInitScript()
    {
        return initScript;
    }

    @Config("db.init-script")
    public H2EmbeddedDataSourceConfig setInitScript(String initScript)
    {
        this.initScript = initScript;
        return this;
    }

    /**
     * This setting can help solve the SQL injection problem. By default, text
     * and number literals are allowed in SQL statements. However, this enables
     * SQL injection if the application dynamically builds SQL statements. SQL
     * injection is not possible if user data is set using parameters ('?').
     * <p/>
     * NONE means literals of any kind are not allowed, only parameters and
     * constants are allowed. NUMBERS mean only numerical and boolean literals
     * are allowed. ALL means all literals are allowed (default).
     */
    // todo this should be an enum
    public String getAllowLiterals()
    {
        return allowLiterals;
    }

    @Config("db.allow-literals")
    public H2EmbeddedDataSourceConfig setAllowLiterals(String allowLiterals)
    {
        this.allowLiterals = allowLiterals;
        return this;
    }

    /**
     * Sets the compression algorithm for BLOB and CLOB data. Compression is
     * usually slower, but needs less disk space. LZF is faster but uses more space.
     * </p>
     * Allowed values are "NO", "LZF" and "DEFLATE"
     */
    // todo this should be an enum
    public String getCompressLob()
    {
        return compressLob;
    }

    @Config("db.compress-lob")
    public void setCompressLob(String compressLob)
    {
        this.compressLob = compressLob;
    }

    /**
     * Sets the size of the cache in KB (each KB being 1024 bytes) for the
     * current database. The default value is 16384 (16 MB). The value is
     * rounded to the next higher power of two. Depending on the virtual
     * machine, the actual memory required may be higher.
     */
    // todo we should have a typed value class for this
    public int getCacheSize()
    {
        return cacheSize;
    }

    @Config("db.cache-size")
    public H2EmbeddedDataSourceConfig setCacheSize(int cacheSize)
    {
        this.cacheSize = cacheSize;
        return this;
    }

    /**
     * Sets the maximum size of an in-place LOB object. LOB objects larger that
     * this size are stored in a separate file, otherwise stored directly in the
     * database (in-place). The default max size is 1024. This setting has no
     * effect for in-memory databases.
     */
    public long getMaxLengthInplaceLob()
    {
        return maxLengthInplaceLob;
    }

    @Config("db.inplace.lob.length.max")
    public H2EmbeddedDataSourceConfig setMaxLengthInplaceLob(long maxLengthInplaceLob)
    {
        this.maxLengthInplaceLob = maxLengthInplaceLob;
        return this;
    }

    /**
     * The maximum number of rows in a result set that are kept in-memory. If
     * more rows are read, then the rows are buffered to disk. The default value
     * is 10000.
     */
    public long getMaxMemoryRows()
    {
        return maxMemoryRows;
    }

    @Config("db.rows.memory.max")
    public H2EmbeddedDataSourceConfig setMaxMemoryRows(long maxMemoryRows)
    {
        this.maxMemoryRows = maxMemoryRows;
        return this;
    }
}