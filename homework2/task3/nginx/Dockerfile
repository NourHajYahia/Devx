# nginx/Dockerfile
FROM openresty/openresty:alpine

# Copy the Nginx configuration file
COPY nginx/nginx.conf /usr/local/openresty/nginx/conf/nginx.conf

# Copy the HTML file
COPY html/index.html /usr/local/openresty/nginx/html/index.html

EXPOSE 80