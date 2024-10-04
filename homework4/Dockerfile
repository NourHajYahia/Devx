# Use the official Nginx base image
FROM nginx:alpine

# Copy the HTML file into the container's web server location
COPY index.html /usr/share/nginx/html/index.html

# Expose port 80
EXPOSE 80

# Start Nginx when the container launches
CMD ["nginx", "-g", "daemon off;"]
