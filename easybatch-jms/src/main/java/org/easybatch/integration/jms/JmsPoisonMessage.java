/*
 * The MIT License
 *
 *  Copyright (c) 2015, Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.easybatch.integration.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;

/**
 * A End-Of-Stream {@link Message} implementation .
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class JmsPoisonMessage implements Message, Serializable {

    private Properties properties = new Properties();

    public JmsPoisonMessage() {
        properties.setProperty("type", "poison");
    }

    @Override
    public String getJMSMessageID() throws JMSException {
        return null;
    }

    @Override
    public void setJMSMessageID(String s) throws JMSException {
    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSTimestamp(long l) throws JMSException {
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        return new byte[0];
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] bytes) throws JMSException {
    }

    @Override
    public void setJMSCorrelationID(String s) throws JMSException {
    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        return "";
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        return null;
    }

    @Override
    public void setJMSReplyTo(Destination destination) throws JMSException {
    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        return null;
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {
    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSDeliveryMode(int i) throws JMSException {
    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        return false;
    }

    @Override
    public void setJMSRedelivered(boolean b) throws JMSException {
    }

    @Override
    public String getJMSType() throws JMSException {
        return "org.easybatch.integration.jms.JmsPoisonMessage";
    }

    @Override
    public void setJMSType(String s) throws JMSException {
    }

    @Override
    public long getJMSExpiration() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSExpiration(long l) throws JMSException {
    }

    @Override
    public int getJMSPriority() throws JMSException {
        return 0;
    }

    @Override
    public void setJMSPriority(int i) throws JMSException {
    }

    @Override
    public void clearProperties() throws JMSException {
    }

    @Override
    public boolean propertyExists(String s) throws JMSException {
        return properties.contains(s);
    }

    @Override
    public boolean getBooleanProperty(String s) throws JMSException {
        return false;
    }

    @Override
    public byte getByteProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public short getShortProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public int getIntProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public long getLongProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public float getFloatProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public double getDoubleProperty(String s) throws JMSException {
        return 0;
    }

    @Override
    public String getStringProperty(String s) throws JMSException {
        return properties.getProperty(s);
    }

    @Override
    public Object getObjectProperty(String s) throws JMSException {
        return properties.getProperty(s);
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        return properties.propertyNames();
    }

    @Override
    public void setBooleanProperty(String s, boolean b) throws JMSException {
    }

    @Override
    public void setByteProperty(String s, byte b) throws JMSException {
    }

    @Override
    public void setShortProperty(String s, short i) throws JMSException {
    }

    @Override
    public void setIntProperty(String s, int i) throws JMSException {
    }

    @Override
    public void setLongProperty(String s, long l) throws JMSException {
    }

    @Override
    public void setFloatProperty(String s, float v) throws JMSException {
    }

    @Override
    public void setDoubleProperty(String s, double v) throws JMSException {
    }

    @Override
    public void setStringProperty(String s, String s2) throws JMSException {
    }

    @Override
    public void setObjectProperty(String s, Object o) throws JMSException {
    }

    @Override
    public void acknowledge() throws JMSException {
    }

    @Override
    public void clearBody() throws JMSException {
    }

}
