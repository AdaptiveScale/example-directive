package org.example.directives;

import com.idealista.fpe.FormatPreservingEncryption;
import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.annotation.Plugin;
import io.cdap.wrangler.api.*;
import io.cdap.wrangler.api.parser.ColumnName;
import io.cdap.wrangler.api.parser.TokenType;
import io.cdap.wrangler.api.parser.UsageDefinition;
import org.example.directives.utils.FormatPreservingUtil;

import java.util.List;

@Plugin(type = Directive.TYPE)
@Name(FormatPreservingEncrypt.NAME)
@Description("Format Preserving Encryption for a column")
public class FormatPreservingEncrypt implements Directive {

    public static final String NAME = "fp-encrypt";
    private String column;
    private FormatPreservingEncryption formatPreservingEncryption;

    @Override
    public UsageDefinition define() {
        // fp-encrypt :column
        UsageDefinition.Builder builder = UsageDefinition.builder(NAME);
        builder.define("column", TokenType.COLUMN_NAME);
        return builder.build();
    }

    @Override
    public void initialize(final Arguments arguments) throws DirectiveParseException {
        this.column = ((ColumnName) arguments.value("column")).value();
        this.formatPreservingEncryption = FormatPreservingUtil.defaultFormatPreservingEncryption();
    }

    @Override
    public List<Row> execute(final List<Row> rows, final ExecutorContext executorContext) throws DirectiveExecutionException, ErrorRowException, ReportErrorAndProceed {
        for (Row row : rows) {
            int idx = row.find(column);
            if (idx != -1) {
                Object object = row.getValue(idx);
                row.setValue(idx, formatPreservingEncryption.encrypt(object.toString(), FormatPreservingUtil.tweak));
            }
        }
        return rows;
    }

    @Override
    public void destroy() {
        //no-op
    }
}
