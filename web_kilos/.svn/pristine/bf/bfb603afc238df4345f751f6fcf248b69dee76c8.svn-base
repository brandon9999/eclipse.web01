package com.javacan.wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;

import com.javacan.beanutil.BeanUtil;
import java.lang.reflect.InvocationTargetException;

/**
 * FieldMapping의 정보를 사용하여 ResultSet으로부터 데이터를
 * 빈 객체에 복사한다던가, 또는 PreparedStatement의 파라미터에
 * 빈 객체의 데이터를 지정하는 작업을 처리해준다.
 * @author 최범균
 */
public class FieldMappingUtil {
	public static void copyResultSetToBean(ResultSet rs, 
	        FieldMapping mapping, Object bean)
	throws SQLException, NoSuchMethodException, InvocationTargetException,
	       IllegalAccessException {
    	Object rsValue = null;
    	
    	switch(mapping.getFieldType()) {
    		case Types.CHAR:
    		case Types.VARCHAR:
    		    rsValue = rs.getString(mapping.getFieldName());
    		    break;
		    case Types.LONGVARCHAR:
                Reader reader = null;
                StringBuffer valueBuffer = new StringBuffer(512);
                char[] charBuff = new char[125];
                int readCount = -1;
                try {
                    reader = rs.getCharacterStream(mapping.getFieldName());
                    if (reader != null) {
                        while( (readCount = reader.read(charBuff)) != -1) {
                            valueBuffer.append(charBuff, 0, readCount);
                        }
                    }
                    rsValue = valueBuffer.toString();
                } catch(IOException ex) {
                } finally {
                    if (reader != null) try { reader.close(); } catch(IOException ex) {}
                }
                break;
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.BIGINT:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
                rsValue = rs.getString(mapping.getFieldName());
                break;
            case Types.DATE:
                rsValue = rs.getDate(mapping.getFieldName());
                break;
            case Types.TIME:
                rsValue = rs.getTime(mapping.getFieldName());
                break;
            case Types.TIMESTAMP:
                rsValue = rs.getTimestamp(mapping.getFieldName());
                break;
		    default:
		        throw new IllegalArgumentException(
		            "Invalid SQL Type:"+mapping.getFieldType());
    	}
    	
    	Class propertyType = mapping.getPropertyType();
    	Object propertyValue = null;
    	
    	if (propertyType == String.class) {
    		if (rsValue instanceof String) {
    		    propertyValue = rsValue;
    		} else {
			    propertyValue = rsValue.toString();
			}
		} else if (propertyType == Integer.TYPE) {
			if (rsValue instanceof String) {
				propertyValue = new Integer(Integer.parseInt((String)rsValue));
			}
		} else if (propertyType == Long.TYPE) {
			if (rsValue instanceof String) {
				propertyValue = new Long(Long.parseLong((String)rsValue));
			}
		} else if (propertyType == Short.TYPE) {
			if (rsValue instanceof String) {
				propertyValue = new Short(Short.parseShort((String)rsValue));
			}
		} else if (propertyType == Double.TYPE) {
			if (rsValue instanceof String) {
				propertyValue = new Double(Double.parseDouble((String)rsValue));
			}
		} else if (propertyType == Float.TYPE) {
			if (rsValue instanceof String) {
				propertyValue = new Float(Float.parseFloat((String)rsValue));
			}
		} else if (propertyType == Character.TYPE) {
			if (rsValue instanceof String && ((String)rsValue).length() > 0) {
				propertyValue = new Character(((String)rsValue).charAt(0));
			}
		} else if (propertyType == Boolean.TYPE) {
			if (rsValue instanceof String) {
				if (rsValue.equals("T") || rsValue.equals("t") || rsValue.equals("1")) {
					propertyValue = new Boolean(true);
			    } else {
			    	propertyValue = new Boolean(false);
		    	}
			}
		} else if (propertyType == java.util.Date.class ||
		           propertyType == java.sql.Timestamp.class ||
		           propertyType == java.sql.Date.class ||
		           propertyType == java.sql.Time.class) {
			long timeValue = -1;
			
			if (rsValue instanceof java.sql.Timestamp) {
			    timeValue = ((java.sql.Timestamp)rsValue).getTime();	
			} if (rsValue instanceof java.sql.Date) {
				timeValue = ((java.sql.Date)rsValue).getTime();
			} else if (rsValue instanceof java.sql.Time) {
				timeValue = ((java.sql.Time)rsValue).getTime();
			}
			
			if (timeValue != -1) {
				if (propertyType == java.util.Date.class) {
					propertyValue = new java.util.Date(timeValue);
				} else if (propertyType == java.sql.Timestamp.class) {
					propertyValue = new java.sql.Timestamp(timeValue);
				} else if (propertyType == java.sql.Date.class) {
					propertyValue = new java.sql.Date(timeValue);
				} else if (propertyType == java.sql.Time.class) {
					propertyValue = new java.sql.Time(timeValue);
				}
			}
		}
		if (propertyValue != null) {
			BeanUtil.setProperty(bean, mapping.getPropertyName(), propertyValue);
		}
	}
	
	public static void setPreparedParameter(PreparedStatement pstmt, int index,
	        FieldMapping mapping, Object propertyValue)
    throws SQLException {
    	Class valueClass = propertyValue.getClass();
    	Class propertyType = mapping.getPropertyType();
    	int sqlType = mapping.getFieldType();
    	
    	switch(mapping.getFieldType()) {
    		case Types.CHAR:
    		case Types.VARCHAR:
    		    if (valueClass == String.class) {
    		        pstmt.setString(index, (String)propertyValue);
		        } else if (valueClass == Boolean.class &&
		                   propertyType == Boolean.TYPE) {
	                if (((Boolean)propertyValue).booleanValue()) {
	                	pstmt.setString(index, "T");
                	} else {
                		pstmt.setString(index, "F");
            		}
               	} else {
		        	pstmt.setString(index, propertyValue.toString());
	        	}
    		    break;
		    case Types.LONGVARCHAR:
                Reader reader = null;
                try {
                	String strValue = propertyValue.toString();
                    reader = new StringReader(strValue);
                    pstmt.setCharacterStream(index, reader, strValue.length());
                } finally {
                    if (reader != null) try { reader.close(); } catch(IOException ex) {}
                }
                break;
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.BIGINT:
                if (valueClass == Boolean.class && propertyType == Boolean.TYPE) {
	                if (((Boolean)propertyValue).booleanValue()) {
	                	pstmt.setString(index, "T");
                	} else {
                		pstmt.setString(index, "F");
            		}
            		break;
               	}
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.DECIMAL:
                if (valueClass == Integer.class) {
                    pstmt.setInt(index, ((Integer)propertyValue).intValue());
                } else if (valueClass == Short.class) {
                    pstmt.setShort(index, ((Short)propertyValue).shortValue());
                } else if (valueClass == Long.class) {
                    pstmt.setLong(index, ((Long)propertyValue).longValue());
                } else if (valueClass == Float.class) {
                    pstmt.setFloat(index, ((Float)propertyValue).floatValue());
                } else if (valueClass == Double.class) {
                    pstmt.setDouble(index, ((Double)propertyValue).doubleValue());
                } else if (valueClass == java.lang.String.class) {
                    try {
                        if (sqlType == Types.TINYINT || 
                                sqlType == Types.SMALLINT ||
                                sqlType == Types.INTEGER) {
                            pstmt.setInt(index, Integer.parseInt(propertyValue.toString()) );
                        } else if (sqlType == Types.BIGINT) {
                            pstmt.setLong(index, Long.parseLong(propertyValue.toString()) );
                        } else {
                            pstmt.setDouble(index, Double.parseDouble(propertyValue.toString()) );
                        }
                    } catch(Exception ex) {}
                }
                break;
            case Types.DATE:
                if (valueClass == java.sql.Date.class) {
                    pstmt.setDate(index, (java.sql.Date)propertyValue);
                } else if (valueClass == java.util.Date.class) {
                    java.sql.Date valueDate = new java.sql.Date( ((java.util.Date)propertyValue).getTime() );
                    pstmt.setDate(index, valueDate);
                }
                break;
            case Types.TIME:
                if (valueClass == java.sql.Time.class) {
                    pstmt.setTime(index, (java.sql.Time)propertyValue);
                } else if (valueClass == java.util.Date.class) {
                    java.sql.Time valueTime = new java.sql.Time( ((java.util.Date)propertyValue).getTime() );
                    pstmt.setTime(index, valueTime);
                }
                break;
            case Types.TIMESTAMP:
                if (valueClass == java.sql.Timestamp.class) {
                    pstmt.setTimestamp(index, (java.sql.Timestamp)propertyValue);
                } else if (valueClass == java.util.Date.class) {
                    java.sql.Timestamp valueTS = new java.sql.Timestamp( ((java.util.Date)propertyValue).getTime() );
                    pstmt.setTimestamp(index, valueTS);
                }
                break;
		    default:
		        throw new IllegalArgumentException(
		            "Invalid SQL Type:"+mapping.getFieldType());
		}
	}
}
