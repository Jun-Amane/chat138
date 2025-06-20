import React, { useState, useRef, useEffect } from "react";
import {
  Box,
  TextField,
  IconButton,
  Typography,
  List,
  ListItem,
  ListItemText,
  CircularProgress,
  useTheme,
  InputAdornment,
  Avatar,
} from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import PersonIcon from "@mui/icons-material/Person";
import SmartToyIcon from "@mui/icons-material/SmartToy";
import ErrorIcon from "@mui/icons-material/Error";
import NavBar from "./NavBar";
import TypewriterEffect from "./TypewriterEffect";
// import axios from 'axios';

import { chatService } from "@/services/chatService";
import { MessageDto } from "@/types/chat";
import { useRouter } from "next/navigation";
import { MessageRequest } from "@/types/chat";

import { router } from "next/client";

import { useChatContext } from "@/app/components/layouts/DashboardLayout";

interface ChatInterfaceProps {
  uuid?: string;
}

const ChatInterface: React.FC<ChatInterfaceProps> = ({ uuid }) => {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState<MessageDto[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const messagesEndRef = useRef<HTMLDivElement>(null);
  const theme = useTheme();
  const [isTyping, setIsTyping] = useState(false);

  const { refreshHistory } = useChatContext();

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInput(event.target.value);
  };

  const router = useRouter();

  useEffect(() => {
    if (!uuid) {
      setMessages([]); // 清空消息
      return;
    }

    const fetchChatHistory = async () => {
      setIsLoading(true);
      try {
        const data = await chatService.getChatDetail(uuid);
        setMessages(data.messages);
      } catch (error) {
        setMessages([
          {
            role: "error",
            content:
              error instanceof Error ? error.message : "加载历史消息失败",
          },
        ]);
      } finally {
        setIsLoading(false);
      }
    };

    fetchChatHistory();
    refreshHistory();
  }, [uuid]);

  const handleSendMessage = async (message: string = input) => {
    if (message.trim() === "" || isLoading) return;

    const userMessage = { message };

    setInput("");
    setIsLoading(true);

    try {
      let data: any;
      if (uuid) {
        // 继续对话
        data = await chatService.continueChat(uuid, userMessage);
        setMessages((prev) => [...prev, ...data.messages]);
      } else {
        // 新对话
        data = await chatService.createChat(userMessage);
        setMessages((prev) => [...prev, ...data.messages]);
        router.push(`/chat/${data.uuid}`);
      }
    } catch (error) {
      const errorMessage: MessageDto = {
        content: error instanceof Error ? error.message : "发生错误",
        role: "error",
      };
      setMessages((prev) => [...prev, errorMessage]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === "Enter" && (e.ctrlKey || e.metaKey)) {
      e.preventDefault();
      handleSendMessage();
    }
  };

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleTypingComplete = () => {
    setIsTyping(false);
  };

  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        bgcolor: theme.palette.background.default,
      }}
    >
      <NavBar />
      <Box sx={{ display: "flex", flexGrow: 1 }}>
        <Box
          sx={{
            flexGrow: 1,
            display: "flex",
            flexDirection: "column",
            bgcolor: "#F4F4F4",
          }}
        >
          <Typography
            variant="subtitle1"
            sx={{ p: 2, color: theme.palette.text.secondary }}
          >
            AgriSageLite Knowledge Base
          </Typography>

          <Box
            sx={{
              flexGrow: 1,
              overflowY: "auto",
              px: { xs: 2, sm: 4, md: 6 },
              py: 2,
              "&::-webkit-scrollbar": { width: "0.4em" },
              "&::-webkit-scrollbar-thumb": {
                backgroundColor: "rgba(0,0,0,.1)",
                borderRadius: "10px",
              },
            }}
          >
            <List>
              {messages.map((message, index) => (
                <ListItem
                  key={index}
                  sx={{
                    justifyContent:
                      message.role == "user" ? "flex-end" : "flex-start",
                    mb: 1,
                    alignItems: "flex-start",
                  }}
                >
                  {!(message.role == "user") && (
                    <Avatar
                      sx={{
                        bgcolor:
                          message.role == "error"
                            ? theme.palette.error.main
                            : theme.palette.secondary.main,
                        mr: 1,
                      }}
                    >
                      {message.role == "error" ? (
                        <ErrorIcon />
                      ) : (
                        <SmartToyIcon />
                      )}
                    </Avatar>
                  )}
                  <Box
                    sx={{
                      p: 2,
                      bgcolor:
                        message.role == "user"
                          ? theme.palette.primary.light
                          : message.role == "error"
                            ? theme.palette.error.light
                            : theme.palette.background.paper,
                      color:
                        message.role == "user"
                          ? theme.palette.primary.contrastText
                          : message.role == "error"
                            ? theme.palette.error.contrastText
                            : theme.palette.text.primary,
                      borderRadius:
                        message.role == "user"
                          ? "20px 20px 0 20px"
                          : "20px 20px 20px 0",
                      maxWidth: "70%",
                      wordBreak: "break-word",
                      boxShadow: 1,
                    }}
                  >
                    {isTyping ? (
                      <TypewriterEffect
                        text={message.content}
                        onComplete={() => handleTypingComplete()}
                      />
                    ) : (
                      <ListItemText
                        primary={message.content}
                        primaryTypographyProps={{
                          sx: { wordBreak: "break-word" },
                        }}
                        secondaryTypographyProps={{ fontSize: "0.75rem" }}
                      />
                    )}
                  </Box>
                  {message.role == "user" && (
                    <Avatar sx={{ bgcolor: theme.palette.primary.main, ml: 1 }}>
                      <PersonIcon />
                    </Avatar>
                  )}
                </ListItem>
              ))}
            </List>
            <div ref={messagesEndRef} />
          </Box>

          <Box sx={{ p: 2, bgcolor: "#F4F4F4" }}>
            <Box sx={{ maxWidth: 800, mx: "auto" }}>
              <TextField
                fullWidth
                multiline
                minRows={1}
                maxRows={4}
                value={input}
                onChange={handleInputChange}
                onKeyDown={handleKeyDown}
                placeholder="请输入问题…"
                disabled={isLoading}
                variant="outlined"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <IconButton
                        onClick={() => handleSendMessage()}
                        disabled={isLoading || input.trim() === ""}
                      >
                        {isLoading ? (
                          <CircularProgress size={24} />
                        ) : (
                          <SendIcon />
                        )}
                      </IconButton>
                    </InputAdornment>
                  ),
                  sx: {
                    bgcolor: theme.palette.background.paper,
                    borderRadius: "20px",
                    "& fieldset": { border: "none" },
                  },
                }}
              />
              <Typography
                variant="caption"
                sx={{
                  mt: 1,
                  display: "block",
                  textAlign: "center",
                  color: theme.palette.text.secondary,
                }}
              >
                大模型可能会出错，请注意甄别。按 Ctrl + Enter 发送。
              </Typography>
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default ChatInterface;
